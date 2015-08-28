package com.superdroid.base.utils;

import android.os.Environment;

import java.io.File;

/**
 * 文件处理类
 */
public class FileUtil {

	private static final String ROOT_DIR = "baseApp";
	private static final String FILE_CACHE = "data";
	private static final String IMG_CACHE = "img";
	private static final String DOWNLOAD = "download";

	/**
	 * 获取缓存路径
	 * 
	 * @return
	 */
	public static String getJsonCachePath() {
		if (isSdAvaliable()) {
			return getSdCachePath(FILE_CACHE);
		} else {
			// 缓存到内部存储
			return getInternalCacheDir(FILE_CACHE);
		}
	}

	/**
	 * 获取apk下载路径
	 * 
	 * @return
	 */
	public static String getDownloadPath() {
		if (isSdAvaliable()) {
			return getSdCachePath(DOWNLOAD);
		} else {
			return getInternalCacheDir(DOWNLOAD);
		}
	}

	/**
	 * 获取图片缓存路径
	 * 
	 * @return
	 */
	public static String getImgCachePath() {
		if (isSdAvaliable()) {
			return getSdCachePath(IMG_CACHE);
		} else {
			// 缓存到内部存储
			return getInternalCacheDir(IMG_CACHE);
		}
	}

	/**
	 * 获得内部缓存cache目录
	 * 
	 * @param cacheDir
	 * @return
	 */
	private static String getInternalCacheDir(String cacheDir) {
		StringBuilder builder = new StringBuilder();
		// 内部缓存cache路径
		String absolutePath = UIUtil.getContext().getCacheDir()
				.getAbsolutePath();
		builder.append(absolutePath);
		builder.append(File.separator);
		builder.append(cacheDir);
		builder.append(File.separator);
		return builder.toString();
	}

	/**
	 * 创建SD卡缓存路径
	 * 
	 * @param cacheDir
	 *            存放缓存文件的文件夹
	 * @return
	 */
	private static String getSdCachePath(String cacheDir) {
		String path;
		StringBuilder builder = new StringBuilder();
		String absolutePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		builder.append(absolutePath);
		builder.append(File.separator);
		builder.append(ROOT_DIR);
		builder.append(File.separator);
		builder.append(cacheDir);
		builder.append(File.separator);
		path = builder.toString();
		if (createFileDirs(path)) {
			return path;
		} else {
			return "";
		}
	}

	/**
	 * 创建文件目录
	 * 
	 * @param path
	 * @return
	 */
	private static boolean createFileDirs(String path) {
		File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
			return file.mkdirs();
		}
		return true;
	}

	/**
	 * 判断SD卡是否可用
	 * 
	 * @return
	 */
	private static boolean isSdAvaliable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

}
