package File;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {
    private static void showFileDetail(File file) {
        System.out.println("path: " + file.getPath());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("name: " + file.getName());
        System.out.println("parent: " + file.getParent());
        System.out.println(file.isDirectory() ? "Directory" : file.isFile() ? "File" : " not file or directory");
        System.out.println("canwrite: " + file.canWrite());
    }

    public static void main(String[] args) {
        String path = "../";
        if (args.length > 0) {
            path = args[0];
        }
        File file = new File(path);
        System.out.println(file.getName());
        showFileDetail(file);
        String[] list;
        if (args.length > 1) {
            list = file.list(new DirFileter(args[1]));
        } else {
            list = file.list();
        }

        System.out.println(list);
        if (list.length > 0) {
            for (String l : list
            ) {
                System.out.println(l);
            }
        } else
            System.out.println("empty");
    }
}

class DirFileter implements FilenameFilter{
    private Pattern pattern;
    public DirFileter(String reg){
        pattern = Pattern.compile(reg);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
