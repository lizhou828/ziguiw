package com.zigui.utils;

public class IMAGE_SIZE {
	
	
	 private final static int[][] sizes = {
         {50, 50}, //X
         {68, 68}, //S
         {160, 160},//M
         {350, 350} //L
 };

	 public final static String DEFAULT_IMAGE = "/images/newImages/default.jpg";
	
	 /* 商品详细页缩略图 */
	 public final static int X_WIDTH = sizes[0][0];
	 public final static int X_HEIGHT = sizes[0][1];
	 public final static String X = X_WIDTH + "X" + X_HEIGHT;
	 /* 推荐区 */
	 public final static int S_WIDTH = sizes[1][0];
	 public final static int S_HEIGHT = sizes[1][1];
	 public final static String S = S_WIDTH + "X" + S_HEIGHT;
	 /* 商品列表页图 */
	 public final static int M_WIDTH = sizes[2][0];
	 public final static int M_HEIGHT = sizes[2][1];
	 public final static String M = M_WIDTH + "X" + M_HEIGHT;
	 /* 商品详细页大图 */
	 public final static int L_WIDTH = sizes[3][0];
	 public final static int L_HEIGHT = sizes[3][1];
	 public final static String L = L_WIDTH + "X" + L_HEIGHT;

}
