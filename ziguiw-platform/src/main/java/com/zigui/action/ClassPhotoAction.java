package com.zigui.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.ClassPhoto;
import com.zigui.domain.Clasz;
import com.zigui.domain.School;
import com.zigui.service.impl.ClassInfoServiceImpl;
import com.zigui.utils.ConfigUtils;
import com.zigui.utils.FileUtil;
import com.zigui.utils.LogTool;
import com.zigui.utils.Page;
import com.zigui.utils.StrutsPager;
import com.zigui.utils.enums.ClassPhotoType;
import com.zigui.utils.enums.IsFrontCover;
import com.zigui.utils.enums.IsRecommended;
import com.zigui.utils.enums.NewsStatus;
import com.zigui.utils.enums.ResourceFolder;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 12-12-20 Time: 下午4:32
 */
public class ClassPhotoAction extends BaseAction {
	private List<ClassPhoto> classPhotos;
	private long class_id;
	private Page<ClassPhoto> classMovies1;
	private Page<ClassPhoto> classMovies2;
	private Page<ClassPhoto> classMovies3;
	private Page<ClassPhoto> classMovies4;
	private int pageNum = 1;
	private int pageSize = 5;
	private int type;
	private Long classMovieId;
	private ClassPhoto classPhotoInfo;

	private ClassPhoto classPhotoItem;
	private Page<ClassPhoto> classPhotoList;
	private File media;

	private String mediaFileName;
	private Map classPhotoTypeList;
	private Map isFrontCoverList;
	private List<School> schoolList;
	private List<Clasz> classList;
	private Map<String, String> query;
	private String queryString;
	
	private StrutsPager strutsPager;
	
    private Long id;
    
    private Map isRecommendedList;

    private @Getter @Setter Long photoId;

	@Autowired
	private ClassInfoServiceImpl classInfoService;

	public ClassPhotoAction() {
		if (classList == null) {
			classList = new ArrayList<Clasz>();
			Clasz tmp = new Clasz();
			tmp.setBj_mcheng("-请选择班级-");
			classList.add(tmp);
		}
	}

	public String getClassPhoto() {
		classPhotos = classInfoService.getClassPhotoByClassId(class_id);
		return Action.SUCCESS;
	}

	public String getClassMovie() {
		classMovies1 = classInfoService.getClassSpByClassid(1, pageSize,
				type, class_id);
		classMovies2 = classInfoService.getClassSpByClassid(2, pageSize,
				type, class_id);
		classMovies3 = classInfoService.getClassSpByClassid(3, pageSize,
				type, class_id);
		classMovies4 = classInfoService.getClassSpByClassid(4, pageSize,
				type, class_id);
		return "movie";
	}

	public String view() {
		schoolList = schoolService.getSchoolList();
		classPhotoTypeList = ClassPhotoType.getList();
		isFrontCoverList = IsFrontCover.getList();
		isRecommendedList = IsRecommended.getList();
		if (class_id != 0) {
			classPhotoItem = classInfoService
					.getClassSinglePhotoByClassId(class_id);
			if (classPhotoItem != null && classPhotoItem.getXx_id() != null
					&& !classPhotoItem.getXx_id().equals("")) {
				classList = classService.findAllClassBySchoolId(classPhotoItem
						.getXx_id());
			}
		}		
		return "view";
	}

	public String save() {
		// save file
		String resourceFolder = null;
		if (classPhotoItem.getType() == ClassPhotoType.PHOTO.getCode()) {
			resourceFolder = ResourceFolder.IMAGE.getFolder();
		}
		if (classPhotoItem.getType() == ClassPhotoType.VIDEO.getCode()) {
			resourceFolder = ResourceFolder.VIDEO.getFolder();
		}
		if (resourceFolder == null) {
			throw new RuntimeException("resource folder not detected!");
		}

		if (classPhotoItem.getCreate_time() == null) {
			classPhotoItem.setCreate_time(new Date());
		}
		
		classPhotoItem.setStatus(NewsStatus.AUDIT_OK.getCode());

		classPhotoItem.setUrl(FileUtil.copyAndCompressImage(media,
				mediaFileName, ConfigUtils.getProperty("static.savedPath"),
				resourceFolder, null));
		// save data
		classInfoService.saveOrUpdateClassPhoto(classPhotoItem);
		return Action.SUCCESS;
	}

	public String list() {
		schoolList = schoolService.getSchoolList();
		classPhotoTypeList = ClassPhotoType.getList();
		isFrontCoverList = IsFrontCover.getList();
		strutsPager.queryList(ClassPhoto.class, "id", false);	
		return "list";
	}
	
	public String delete()
	{
		classInfoService.deleteClassPhotoById(id);		
		return Action.SUCCESS;
	}

	public String getClassMovieInfo() {
		classPhotoInfo = classInfoService.getClassPhotoById(classMovieId);
		classMovies1 = classInfoService.getClassSpByClassid(1, 5, 1,
				class_id);
		classMovies2 = classInfoService.getClassSpByClassid(2, 5, 1,
				class_id);
		return "movieInfo";
	}

	public List<ClassPhoto> getClassPhotos() {
		return classPhotos;
	}

	public void setClassPhotos(List<ClassPhoto> classPhotos) {
		this.classPhotos = classPhotos;
	}

	public long getClass_id() {
		return class_id;
	}

	public void setClass_id(long class_id) {
		this.class_id = class_id;
	}

	public Page<ClassPhoto> getClassMovies2() {
		return classMovies2;
	}

	public void setClassMovies2(Page<ClassPhoto> classMovies2) {
		this.classMovies2 = classMovies2;
	}

	public Page<ClassPhoto> getClassMovies3() {
		return classMovies3;
	}

	public void setClassMovies3(Page<ClassPhoto> classMovies3) {
		this.classMovies3 = classMovies3;
	}

	public Page<ClassPhoto> getClassMovies1() {
		return classMovies1;
	}

	public void setClassMovies1(Page<ClassPhoto> classMovies1) {
		this.classMovies1 = classMovies1;
	}

	public Page<ClassPhoto> getClassMovies4() {
		return classMovies4;
	}

	public void setClassMovies4(Page<ClassPhoto> classMovies4) {
		this.classMovies4 = classMovies4;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ClassPhoto getClassPhotoItem() {
		return classPhotoItem;
	}

	public void setClassPhotoItem(ClassPhoto classPhotoItem) {
		this.classPhotoItem = classPhotoItem;
	}

	public String getMediaFileName() {
		return mediaFileName;
	}

	public void setMediaFileName(String mediaFileName) {
		this.mediaFileName = mediaFileName;
	}

	public Map getClassPhotoTypeList() {
		return classPhotoTypeList;
	}

	public Map getFrontCoverList() {
		return isFrontCoverList;
	}

	public List<Clasz> getClassList() {
		return classList;
	}

	public List<School> getSchoolList() {
		return schoolList;
	}

	public Page<ClassPhoto> getClassPhotoList() {
		return classPhotoList;
	}

	public Long getClassMovieId() {
		return classMovieId;
	}

	public void setClassMovieId(Long classMovieId) {
		this.classMovieId = classMovieId;
	}

	public ClassPhoto getClassPhotoInfo() {
		return classPhotoInfo;
	}

	public void setClassPhotoInfo(ClassPhoto classPhotoInfo) {
		this.classPhotoInfo = classPhotoInfo;
	}

	public File getMedia() {
		return media;
	}

	public void setMedia(File media) {
		this.media = media;
	}	

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map getIsRecommendedList() {
		return isRecommendedList;
	}

	public void setStrutsPager(StrutsPager strutsPager) {
		this.strutsPager = strutsPager;
	}

	public StrutsPager getStrutsPager() {
		return strutsPager;
	}

}
