package qthfile;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;


public class FileManager {

	public static void main(String[] args) {

		Scanner st = new Scanner(System.in);

		while (true) {
			prompt();// 輸出提示信息
			int choice = st.nextInt();
			try {
				switch (choice) {

				case 1:
					System.out.println("you choose 1");
					new CreateFile();
					break;
				case 2:
					System.out.println("you choose 2");
					new DeleteFile();
					break;
				case 3:
					System.out.println("you choose 3");
					new EnterDirectory();
					break;
				case 4:
					System.out.println("you choose 4");
					new ListDirectory();
					break;
				
				case 5:
					System.out.println("you choose 5");
					System.out
							.println("input the source file  and the target file:");
					String sourcef = st.next();
					String destf = st.next();
					new CopyFile(sourcef, destf);
					break;
				case 6:
					System.out.println("you choose 6");
					CopyDirectory copydirectory = new CopyDirectory();
					System.out.println("input the source directory:");
					String sourcedir = st.next();
					System.out.println("input the target directory:");
					String destdir = st.next();
					copydirectory.copyFolder(sourcedir, destdir);
					copydirectory.Success();
					break;
				case 7:
					System.out.println("you choose 7");
					// DESencryption deSencryption=
					new DESencryption();
					DESencryption.DES();
					break;
				
				
				case 0:
					System.out.println("you choose to exit");
					System.exit(0);
				default:
					System.out.print("error");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

	public static void prompt() {
		System.out.println("Welcome to the file manager!\n "
				+ "Please choose the operation!");
		System.out.println("'1':Create a directory");
		System.out.println("'2':Delete a directory");
		System.out.println("'3':Enter a directory");
		System.out.println("'4':List a directory");
		
		System.out.println("'5':Copy a file");
		System.out.println("'6':Copy a directory");
		System.out.println("'7':Encrypt a file");
		System.out.println("'0':Exit");
	}

}

class CreateFile {
	CreateFile() throws IOException {
		System.out
				.println("Please input the path where you want to creat a file\n"
						+ "And use / as the file separator");
		Scanner scan = new Scanner(System.in);
		String filePath = scan.next();
		File file = new File(filePath);// 为filePath的路径名创建file对象
		if (file.exists()) {// file对象存在
			System.out.println("please input the file name");
			String fileName = scan.next(); // 输入文件夹的名称
			File fi = new File(filePath + File.separator + fileName);// 实例化File类的对象
			// file.separator表示程序运行平台的文件分隔符，在windows系统下是反斜杠\
			fi.mkdir();// 创建此抽象路径名指定的目录
			// fi.createNewFile();//用于创建文件，而不是目录
			System.out.println("created successfully!");
			// System.out.println(f.getAbsolutePath());
			// System.out.println(f.isFile());
			// System.out.println(f.isDirectory());
		} else
			System.out.println("The directory is not exit!");
		// scan.close();// 关闭扫描器
	}
}

class DeleteFile {
	DeleteFile() {
		System.out
				.println("Please input the directory of the file you want to delete\n"
						+ "And use / as the file separator");
		Scanner scan = new Scanner(System.in);
		String inputDir = scan.next();
		File filePath = new File(inputDir);// 为要删除的文件所在的目录创建
		if (filePath.exists()) {
			System.out.println("Please input the file name you want to delete");
			String inputFile = scan.next();// 输入要删除文件的名称
			File file = new File(inputDir + filePath.separator + inputFile);// 为要删除的文件创建对象

			if (file.delete()) {// 删除文件成功
				System.out.println("Deleted successfully!");
			} else {
				System.out.println("The file is not empty！");// 非空目录，需要调用递归删除
				if (DeleteFile(file))
					System.out
							.println("The non-empty file was deleted successfully!");
			}
		} else
			System.out.println("The directory is not exit!");
		int d = 3;

	}

	public static boolean DeleteFile(File dir) {// 重载函数用于删除非空目录
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录
			for (int i = 0; i < children.length; i++) {
				boolean success = DeleteFile(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 现在目录为空，可以删除
		return dir.delete();
	}

}

class EnterDirectory {
	EnterDirectory() throws IOException {
		System.out.println("Please input the directory you want enter");
		Scanner scan = new Scanner(System.in);
		String dir = scan.next();
		// 文件对话框类
		// FileDialog fDialog=new FileDialog(new Frame(),
		// "Please choose the file to open:", FileDialog.LOAD);
		// fDialog.setDirectory(dir);
		// fDialog.setVisible(true);
		Runtime.getRuntime().exec("cmd.exe /c start " + dir);

	}
}

class ListDirectory {
	/*ListDirectory() throws IOException {
		System.out.println("Input the directory to sort");
		String dir = new Scanner(System.in).next();
		
		File[] list = new File(dir).listFiles();

		System.out.println("Choose the sorted way:");
		System.out.println("-1:TYPE_DEFAULT");
		System.out.println("1:TYPE_MODIFIED_DATE_DOWN");
		System.out.println("2:TYPE_MODIFIED_DATE_UP");
		System.out.println("3:TYPE_SIZE_DOWN");
		System.out.println("4:TYPE_SIZE_UP");
		System.out.println("5:TYPE_NAME");
		System.out.println("7:TYPE_DIR");

		Scanner scan = new Scanner(System.in);
		int choose = scan.nextInt();
		Arrays.sort(list, new FileSorter(choose));
		FileSorter.printFileArray(list);// 调用打印函数
	}*/
	ListDirectory()
	{
    	System.out.println("请输入你想查看的文件夹，如D:/xxx/xxx");
    	String path = (new Scanner(System.in)).nextLine();
		File file=new File(path);
		if(file.exists())
		{
			System.out.println("该目录内容如下：");
			list(file);
			
		}
		else
			System.out.println("目录不存在!");
	}
    
    void list(File file1)
    {
    	
    	File[] list=file1.listFiles();
    	for(int i=0;i<list.length;i++)
    	{
    		if(list[i].isFile())
    			System.out.println(list[i].getPath());
    		else
    			list(list[i]);
    	//		System.out.println(list[i].getPath()+"------"+list[i].getName());
    	}
    	
    }

}

// 复制指定文件
class CopyFile {
	CopyFile(String source, String dest) throws IOException {
		File in = new File(source);
		File out = new File(dest);
		FileInputStream inFile = new FileInputStream(in);
		FileOutputStream outFile = new FileOutputStream(out);
		byte[] buffer = new byte[1024];
		int a = 0;
		while ((a = inFile.read(buffer)) != -1) {
			outFile.write(buffer, 0, a);
		}// end while
		inFile.close();
		outFile.close();
		System.out.print("Success to copy！");
	}
}

// 复制指定目录
class CopyDirectory {
	public void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}
	}

	public void Success() {
		System.out.println("Success to copy the directory!");// 复制目录成功
	}
}

