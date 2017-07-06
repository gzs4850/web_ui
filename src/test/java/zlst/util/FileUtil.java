package zlst.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	
	//创建文件
	public static boolean createFile(String destFileName){
		File file = new File(destFileName);
		//如果文件已存在则返回FALSE
		if(file.exists()){
			return false;
		}
		//如果是文件夹则返回FALSE
		if(destFileName.endsWith(File.separator)){
			return false;
		}
		//如果父目录不存在则创建父目录
		if(!file.getParentFile().exists()){
			//如果父目录创建失败则返回FALSE
			if(!file.getParentFile().mkdirs()){
				return false;
			}
		}
		//创建文件
		try{
			//创建文件成功返回true
			if(file.createNewFile()){
				return true;
			}
			//创建文件失败则返回FALSE
			else{
				return false;
			}
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//创建目录
	public static boolean createDir(String destDirName){
		File dir = new File(destDirName);
		//如果目录已经存在则返回FALSE
		if(dir.exists()){
			return false;
		}
		//创建文件夹成功返回true，否则返回FALSE
		if(dir.mkdirs()){
			return true;
		}else{
			return false;
		}
	}

}
