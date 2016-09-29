package tools;

import java.io.File;

public class DeleteGit {
	public static void main(String[] args) {
		String path = "E:\\ubuntu_nexus5\\share\\android-src-no-out";
		check(new File(path));
	}

	static void check(File root) {
		if (root == null || root.isDirectory() == false) {
			return;
		}

		if (root.getAbsolutePath().endsWith(".git")) {
			deleteDir(root);
			System.out.println("[delete]" + root.getAbsolutePath());
		} else {
			File[] fs = root.listFiles();
			for (File f : fs) {
				check(f);
			}
		}
	}

	static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
	}
}
