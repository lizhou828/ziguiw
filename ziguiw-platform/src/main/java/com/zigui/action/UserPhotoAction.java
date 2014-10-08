package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.UserAlbum;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserPhoto;
import com.zigui.service.impl.UserAlbumServiceImpl;
import com.zigui.service.impl.UserPhotoServiceImpl;
import com.zigui.utils.ConfigUtils;
import com.zigui.utils.FileOperate;
import com.zigui.utils.FileUtil;
import com.zigui.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.*;

public class UserPhotoAction extends BaseAction implements ServletContextAware {

	private static final long serialVersionUID = 6744953041755497181L;

    private static  final Log log = LogFactory.getLog(UserPhotoAction.class);

	private UserPhoto photo;
	List<UserPhoto> photos;
	private UserPhoto prePhoto;
	private UserPhoto nextPhoto;
	private long albumId;
	private long photoId;
	private UserAlbum album;
	
	private File file;
	private String fileFileName;
    private String fileContentType;
    
    private ServletContext context;
    
    private static final String IMG_PATH = "img_path";
    
	private Map<String, String> query;

	private String orderField = "createTime";

	private String orderType = "desc";

	private String countPerPage = "10";

	private String currentPage = "1";
	
	private Page<UserPhoto> userPhotos;

	private long userPhotoId;
	
	private Long[] opIds;
	
	private long recommendRegionId;
	
	@Autowired
	private UserPhotoServiceImpl userPhotoService;
	
	@Autowired
	private UserAlbumServiceImpl userAlbumService;
	public void validateSave(){				
		if(photo.getAlbumId()==0){
			this.addFieldError("photo.albumId", "相册不能为空");
			return;
		}	
		if(file==null){
			this.addFieldError("file", "请选择要上传的图片");
			return;
		}	
	}
	public String save(){
		
		String webPath = "";
		String rootDir = ConfigUtils.getProperty("static.savedPath");
		String objDir = "images";
		if(FileUtil.isImage(fileFileName)){
		    webPath = FileUtil.copyAndCompressImage(file, fileFileName, rootDir, objDir, null);
		}else{
			this.addFieldError("form", "请添加有效图片");
			return Action.INPUT;
		}
		
		/*ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase user = (UserBase)session.get("VALID_USER");
        String extName = StringUtils.substringAfter(fileFileName, ".");
        String webPath;
        try {
			if (ImageUpload.checkIsImage(extName)) {
                webPath = FileOperate.saveFile("images", file, extName);
			} else {
				this.addFieldError("form", "请添加有效图片");
				return Action.INPUT;
			}

		} catch (Throwable e) {
			this.addFieldError("form", "上传图片失败");
			return Action.INPUT;
		}*/
		
        ServletActionContext.getRequest().setAttribute(IMG_PATH, webPath);
		UserBase tmpUser = new UserBase();
        tmpUser.setId(user.getId());
		
        photo.setUser(tmpUser);		
		photo.setUrl(webPath);
		photo.setCreateTime(new Date());
		userPhotoService.save(photo);
        photo.setUser(tmpUser);
		return Action.SUCCESS;
	}
	
	public String getPhotoByAlbumId(){
		photos = userPhotoService.getPhotoByAlbum(albumId);
		
		return Action.SUCCESS;
	}
	
	public String getRoundPhoto(){
		album = userAlbumService.getById(albumId);
		
		photo = userPhotoService.getPhotoById(photoId);
		List<UserPhoto> prePhotos = userPhotoService.getPrePhoto(photoId, albumId, 7);
		List<UserPhoto> nextPhotos = userPhotoService.getNextPhoto(photoId, albumId, 7);
		
		photos = new ArrayList<UserPhoto>();
		if(CollectionUtils.isEmpty(prePhotos)){
			prePhoto = userPhotoService.getLastPhoto(albumId);
		}else{
			prePhoto = prePhotos.get(0);
			UserPhoto[] photoArray = new UserPhoto[prePhotos.size()];
			CollectionUtils.reverseArray(prePhotos.toArray(photoArray));
			photos.addAll(Arrays.asList(photoArray));
		}
		if(photo!=null){
			photos.add(photo);
		}
		
		
		if(CollectionUtils.isEmpty(nextPhotos)){
			nextPhoto = userPhotoService.getFirstPhoto(albumId);
		}else{
			nextPhoto = nextPhotos.get(0);
			photos.addAll(nextPhotos);
		}
		if(photo==null){
			photo=nextPhoto;
		}
		return Action.SUCCESS;
	}

	public String listByCondition() {
		boolean isAsc = false;
		if (orderType !=null && orderType.equals("ASC")) {
			isAsc = true;
		}
		userPhotos = userPhotoService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc,this.queryString);

		return Action.SUCCESS;
	}
	
	public String delPhoto(){
		userPhotoService.delete(new Long[]{photoId});
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		//opIds = new Long[]{knowledgeId};
		userPhotoService.delete(opIds);
		
		return Action.SUCCESS;
	}
	
	public String recommend(){
//		for(long id : opIds){
//			UserPhoto tmpUserPhoto = userPhotoService.getById(id);
//			KnowledgeRecommendRegion region = new KnowledgeRecommendRegion();
//			region.setId(Constant.USER_PHOTO_RECOMMEND_REGION_ID);
//			tmpUserPhoto.setKnowledgeRecommendRegion(region);
//			
//			userPhotoService.save(tmpUserPhoto);
//		}		
		userPhotoService.recommend(opIds,recommendRegionId);
		return Action.SUCCESS;
	}

	public void setPhoto(UserPhoto photo) {
		this.photo = photo;
	}


	public UserPhoto getPhoto() {
		return photo;
	}


	public void setUserPhotoService(UserPhotoServiceImpl userPhotoService) {
		this.userPhotoService = userPhotoService;
	}


	public UserPhotoServiceImpl getUserPhotoService() {
		return userPhotoService;
	}


	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	public String getFileContentType() {
		return fileContentType;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public ServletContext getContext() {
		return context;
	}


	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public List<UserPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<UserPhoto> photos) {
		this.photos = photos;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Page<UserPhoto> getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(Page<UserPhoto> userPhotos) {
		this.userPhotos = userPhotos;
	}

	public long getUserPhotoId() {
		return userPhotoId;
	}

	public void setUserPhotoId(long userPhotoId) {
		this.userPhotoId = userPhotoId;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public UserPhoto getPrePhoto() {
		return prePhoto;
	}

	public void setPrePhoto(UserPhoto prePhoto) {
		this.prePhoto = prePhoto;
	}

	public UserPhoto getNextPhoto() {
		return nextPhoto;
	}

	public void setNextPhoto(UserPhoto nextPhoto) {
		this.nextPhoto = nextPhoto;
	}

	public UserAlbum getAlbum() {
		return album;
	}

	public void setAlbum(UserAlbum album) {
		this.album = album;
	}

	public long getRecommendRegionId() {
		return recommendRegionId;
	}

	public void setRecommendRegionId(long recommendRegionId) {
		this.recommendRegionId = recommendRegionId;
	}
	
	
	
}