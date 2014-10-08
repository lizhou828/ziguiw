package controllers;

import models.Form;
import models.QueryParams;
import play.Logger;
import play.Play;
import play.data.binding.Binder;
import play.data.validation.MaxSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.Model;
import play.db.Model.Factory;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Router;
import play.utils.Java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class CRUD extends Controller {

    @Before
    public static void addType() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        renderArgs.put("type", type);
    }

    public static void index() {
        if (getControllerClass() == CRUD.class) {
            forbidden();
        }
        render("CRUD/index.html");
    }

    public static void list(int page, String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        String where = (String) request.args.get("where");
        if (where == null) {
            String tmpStr = "";
            for (String key : params.all().keySet()) {
                 if (key.startsWith("where.") && key.length() > 6) {
                     String _key = key.substring(key.indexOf(".") + 1);
                     tmpStr += _key + "=" + params.get(key);
                     tmpStr += " and ";
                 }
            }
            if (!"".equals(tmpStr)) {
                where = tmpStr.substring(0, tmpStr.lastIndexOf(" and "));
            }
        }
        if (page < 1) {
            if (session.get(getControllerClass().getName() + "CRUD.list.page") != null) {
                page = Integer.parseInt(session.get(getControllerClass().getName() + "CRUD.list.page"));
            } else {
                page = 1;
            }
        }
        session.put(getControllerClass().getName() + "CRUD.list.page", page);
        if (search == null) search = session.get(getControllerClass().getName() + "CRUD.list.search");
        if (searchFields == null) searchFields = session.get(getControllerClass().getName() + "CRUD.list.searchFields");
        if (orderBy == null) orderBy = session.get(getControllerClass().getName() + "CRUD.list.orderBy");
        if (order == null) order = session.get(getControllerClass().getName() + "CRUD.list.order");
        if (where == null) where = session.get(getControllerClass().getName() + "CRUD.list.where");
        session.put(getControllerClass().getName() + "CRUD.list.search", search);
        session.put(getControllerClass().getName() + "CRUD.list.searchFields", searchFields);
        session.put(getControllerClass().getName() + "CRUD.list.orderBy", orderBy);
        session.put(getControllerClass().getName() + "CRUD.list.order", order);
        session.put(getControllerClass().getName() + "CRUD.list.where", where);
        params.put("search", search);
        QueryParams queryParams = new QueryParams(page, search, searchFields, orderBy, order, where);
        beforeQuery(queryParams);
        Long count = type.count(search, searchFields, queryParams.where);
        if (count <= getDefaultPageSize()) {
            page = 1;
            queryParams.page = 1;
        }
        List<Model> objects = type.findPage(queryParams.page, queryParams.search, queryParams.searchFields,
                queryParams.orderBy, queryParams.order, queryParams.where);
        Long totalCount = type.count(null, null, queryParams.where);
        afterQuery(queryParams, objects, count, totalCount);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }

    public static void show(String id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Model object = type.findById(id);
        notFoundIfNull(object);
        try {
            render(type, object);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", type, object);
        }
    }

    @SuppressWarnings("deprecation")
    public static void attachment(String id, String field) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Model object = type.findById(id);
        notFoundIfNull(object);
        Object att = object.getClass().getField(field).get(object);
        if(att instanceof Model.BinaryField) {
            Model.BinaryField attachment = (Model.BinaryField)att;
            if (attachment == null || !attachment.exists()) {
                notFound();
            }
            response.contentType = attachment.type();
            renderBinary(attachment.get(), attachment.length());
        }
        // DEPRECATED
        if(att instanceof play.db.jpa.FileAttachment) {
            play.db.jpa.FileAttachment attachment = (play.db.jpa.FileAttachment)att;
            if (attachment == null || !attachment.exists()) {
                notFound();
            }
            renderBinary(attachment.get(), attachment.filename);
        }
        notFound();
    }

    public static void save(String id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Model object = type.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/show.html", type, object);
            } catch (TemplateNotFoundException e) {
                render("CRUD/show.html", type, object);
            }
        }
        beforeSave(object);
        object._save();
        afterSave(object);
        flash.success(play.i18n.Messages.get("crud.saved", type.displayName == null ? type.modelName : type.displayName));
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void blank() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Model object = (Model) constructor.newInstance();
        try {
            render(type, object);
        } catch (TemplateNotFoundException e) {
            render("CRUD/blank.html", type, object);
        }
    }

    public static void create() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Model object = (Model) constructor.newInstance();
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/blank.html", type, object);
            } catch (TemplateNotFoundException e) {
                render("CRUD/blank.html", type, object);
            }
        }
        beforeCreate(object);
        object._save();
        afterCreate(object);
        flash.success(play.i18n.Messages.get("crud.created", type.displayName == null ? type.modelName : type.displayName));
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank");
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void delete(String id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Model object = type.findById(id);
        notFoundIfNull(object);
        try {
            beforeDelete(object);
            object._delete();
            afterDelete(object);
        } catch (Exception e) {
            flash.error(play.i18n.Messages.get("crud.delete.error", type.displayName == null ? type.modelName : type.displayName));
            redirect(request.controller + ".show", object._key());
        }
        flash.success(play.i18n.Messages.get("crud.deleted", type.displayName == null ? type.modelName : type.displayName));
        redirect(request.controller + ".list");
    }

    private static void beforeSave(Model object) {
        try {
            Http.Request.current().controllerClass.getMethod("beforeSave", Model.class).invoke(null, object);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    private static void afterSave(Model object) {
        try {
            Http.Request.current().controllerClass.getMethod("afterSave", Model.class).invoke(null, object);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    private static void beforeCreate(Model object) {
        try {
            Http.Request.current().controllerClass.getMethod("beforeCreate", Model.class).invoke(null, object);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }


    private static void afterCreate(Model object) {
        try {
            Http.Request.current().controllerClass.getMethod("afterCreate", Model.class).invoke(null, object);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    private static void beforeDelete(Model object) {
        try {
            Http.Request.current().controllerClass.getMethod("beforeDelete", Model.class).invoke(null, object);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    private static void afterDelete(Model object) {
        try {
            Http.Request.current().controllerClass.getMethod("afterDelete", Model.class).invoke(null, object);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
           //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    private static void beforeQuery(QueryParams queryParams) {
        try {
            Http.Request.current().controllerClass.getMethod("beforeQuery", QueryParams.class).invoke(null, queryParams);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    private static void afterQuery(QueryParams queryParams, List<Model> objects, Long count, Long totalCount) {
        try {
            Http.Request.current().controllerClass.getMethod("afterQuery", QueryParams.class, List.class, Long.class, Long.class)
                    .invoke(null, queryParams, objects, count, totalCount);
        } catch (NoSuchMethodException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        }
    }

    protected static ObjectType createObjectType(Class<? extends Model> entityClass) {
        return new ObjectType(entityClass);
    }

    // ~~~~~~~~~~~~~
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface For {
        Class<? extends Model> value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Exclude {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Hidden {}

    // ~~~~~~~~~~~~~
    static int getDefaultPageSize() {
        return Integer.parseInt(Play.configuration.getProperty("crud.pageSize", "30"));
    }

    public static class ObjectType implements Comparable<ObjectType> {

        public Class<? extends Controller> controllerClass;
        public Class<? extends Model> entityClass;
        public String name;
        public String displayName;
        public String modelName;
        public String controllerName;
        public String keyName;
		public Factory factory;

        public ObjectType(Class<? extends Model> modelClass) {
            this.modelName = modelClass.getSimpleName();
            this.entityClass = modelClass;
            this.factory = Model.Manager.factoryFor(entityClass);
            this.keyName = factory.keyName();
        }

        @SuppressWarnings("unchecked")
        public ObjectType(String modelClass) throws ClassNotFoundException {
            this((Class<? extends Model>) Play.classloader.loadClass(modelClass));
        }

        public static ObjectType forClass(String modelClass) throws ClassNotFoundException {
            return new ObjectType(modelClass);
        }

        public static ObjectType get(Class<? extends Controller> controllerClass) {
            Class<? extends Model> entityClass = getEntityClassForController(controllerClass);
            if (entityClass == null || !Model.class.isAssignableFrom(entityClass)) {
                return null;
            }
            ObjectType type;
            try {
                type = (ObjectType) Java.invokeStaticOrParent(controllerClass, "createObjectType", entityClass);
            } catch (Exception e) {
                Logger.error(e, "Couldn't create an ObjectType. Use default one.");
                type = new ObjectType(entityClass);
            }
            type.name = controllerClass.getSimpleName().replace("$", "");
            type.displayName = type.name;
            Form form = entityClass.getAnnotation(Form.class);
            if (form != null) {
            	type.displayName = form.displayName();
            }
            type.controllerName = controllerClass.getSimpleName().toLowerCase().replace("$", "");
            type.controllerClass = controllerClass;
            return type;
        }

        @SuppressWarnings("unchecked")
        public static Class<? extends Model> getEntityClassForController(Class<? extends Controller> controllerClass) {
            if (controllerClass.isAnnotationPresent(For.class)) {
                return controllerClass.getAnnotation(For.class).value();
            }
            for(Type it : controllerClass.getGenericInterfaces()) {
                if(it instanceof ParameterizedType) {
                    ParameterizedType type = (ParameterizedType)it;
                    if (((Class<?>)type.getRawType()).getSimpleName().equals("CRUDWrapper")) {
                        return (Class<? extends Model>)type.getActualTypeArguments()[0];
                    }
                }
            }
            String name = controllerClass.getSimpleName().replace("$", "");
            name = "models." + name.substring(0, name.length() - 1);
            try {
                return (Class<? extends Model>) Play.classloader.loadClass(name);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        public Object getListAction() {
            return Router.reverse(controllerClass.getName().replace("$", "") + ".list");
        }

        public Object getBlankAction() {
            return Router.reverse(controllerClass.getName().replace("$", "") + ".blank");
        }

        public Long count(String search, String searchFields, String where) {

            return factory.count(searchFields == null ? new ArrayList<String>() : Arrays.asList(searchFields.split("[ ]")), search, where);
        }

        @SuppressWarnings("unchecked")
        public List<Model> findPage(int page, String search, String searchFields, String orderBy, String order, String where) {
            int offset = (page - 1) * getDefaultPageSize();
            List<String> properties = searchFields == null ? new ArrayList<String>(0) : Arrays.asList(searchFields.split("[ ]"));
            return Model.Manager.factoryFor(entityClass).fetch(offset, getDefaultPageSize(), orderBy, order, properties, search, where);
        }

        public Model findById(String id) throws Exception {
            if (id == null) {
                return null;
            }

            Factory factory =  Model.Manager.factoryFor(entityClass);
            Object boundId = Binder.directBind(id, factory.keyType());
            return factory.findById(boundId);
        }


        public List<ObjectField> getFields() {
            List<ObjectField> fields = new ArrayList<ObjectField>();
            List<ObjectField> hiddenFields = new ArrayList<ObjectField>();
            for (Model.Property f : factory.listProperties()) {
                ObjectField of = new ObjectField(f);
                models.Field field = f.field.getAnnotation(models.Field.class);
                if (field != null) {
                	of.displayName = field.displayName();
                }
                if (of.type != null) {
                    if (of.type.equals("hidden")) {
                        hiddenFields.add(of);
                    } else {
                        fields.add(of);
                    }
                }
            }

            hiddenFields.addAll(fields);
            return hiddenFields;
        }

        public ObjectField getField(String name) {
            for (ObjectField field : getFields()) {
                if (field.name.equals(name)) {
                    return field;
                }
            }
            return null;
        }

        @Override
        public int compareTo(ObjectType other) {
            return modelName.compareTo(other.modelName);
        }

        @Override
        public String toString() {
            return modelName;
        }

        public static class ObjectField {

            private Model.Property property;
            public String type = "unknown";
            public String name;
            public boolean multiple;
            public boolean required;
            public String displayName;

            @SuppressWarnings("deprecation")
            public ObjectField(Model.Property property) {
                Field field = property.field;
                this.property = property;
                if (CharSequence.class.isAssignableFrom(field.getType())) {
                    type = "text";
                    if (field.isAnnotationPresent(MaxSize.class)) {
                        int maxSize = field.getAnnotation(MaxSize.class).value();
                        if (maxSize > 100) {
                            type = "longtext";
                        }
                    }
                    if (field.isAnnotationPresent(Password.class)) {
                        type = "password";
                    }
                }
                if (Number.class.isAssignableFrom(field.getType()) || field.getType().equals(double.class) || field.getType().equals(int.class) || field.getType().equals(long.class)) {
                    type = "number";
                }
                if (Boolean.class.isAssignableFrom(field.getType()) || field.getType().equals(boolean.class)) {
                    type = "boolean";
                }
                if (Date.class.isAssignableFrom(field.getType())) {
                    type = "date";
                }
                if (property.isRelation) {
                    type = "relation";
                }
                if (property.isMultiple) {
                    multiple = true;
                }
                if(Model.BinaryField.class.isAssignableFrom(field.getType()) || /** DEPRECATED **/ play.db.jpa.FileAttachment.class.isAssignableFrom(field.getType())) {
                    type = "binary";
                }
                if (field.getType().isEnum()) {
                    type = "enum";
                }
                if (property.isGenerated) {
                    type = null;
                }
                if (field.isAnnotationPresent(Required.class)) {
                    required = true;
                }
                if (field.isAnnotationPresent(Hidden.class)) {
                    type = "hidden";
                }
                if (field.isAnnotationPresent(Exclude.class)) {
                    type = null;
                }
                if (Modifier.isFinal(field.getModifiers())) {
                    type = null;
                }
                name = field.getName();
                displayName = name;
            }

            public List<Object> getChoices() {
                return property.choices.list();
            }
        }
    }
}

