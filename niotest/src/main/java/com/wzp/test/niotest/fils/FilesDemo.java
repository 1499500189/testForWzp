package com.wzp.test.niotest.fils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class FilesDemo {
    public static void main(String[] args) throws IOException {
        //createDirectory  创建目录
        //Path path = Paths.get("d:\\wzp");
        //Files.createDirectories(path);
        //copy   文件复制
        // Path path1 = Paths.get("d:\\d\\01wzp.txt");
        // Path path2 = Paths.get("d:\\d\\02wzp.txt");
        //   Path copu =Files.copy(path1,path2);
        //Path copy =Files.copy(source ,path2, StandardCopyOption.REPLACE_EXISTING);//替换掉已经存在的文件
        //文件的移动，也可以用于重命名
        //    Files.move(path1,path2);
        //文件的删除
        //Files.delete(path);
         walkFileTree();
    }

    //递归遍历目录树的功能
    private static void walkFileTree() {
        Path rootPath = Paths.get("d:\\d");
        String fileToFind = File.separator + "0001.txt";
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                /**
                 * Invoked for a file in a directory.
                 *
                 * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
                 * CONTINUE}.
                 *
                 * @param file
                 * @param attrs
                 */
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String filString = file.toAbsolutePath().toString();
                    if (filString.endsWith(fileToFind)) {
                        System.out.println("file found at path:" + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    //  return super.visitFile(file, attrs);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {

        }

    }

}
