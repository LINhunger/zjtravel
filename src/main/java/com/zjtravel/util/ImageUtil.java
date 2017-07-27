package com.zjtravel.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * 将图片进行截取
 * Created by hunger on 2017/3/13.
 */
public class ImageUtil {

    /**
     * 对图片进行裁剪
     * @param imagePath 图片地址
     * @param newPath 存放图片地址
     * @param x 裁剪起点的 x 坐标
     * @param y 裁剪起点的 y 坐标
     * @param width 图片的长度
     * @param height 图片的高度
     * @throws Exception
     */
    public static void cutImage(String imagePath, String newPath,int x,int y,int width,int height)
        throws Exception{
        FileInputStream fileInPut = null;
        ImageInputStream imageInPut = null;

        try {
            //读取图片
            fileInPut = new FileInputStream(imagePath);
            //获取图片的长宽等信息
            BufferedImage buff = ImageIO.read(new File(imagePath));
            int realWidth = buff.getWidth();
            int realHeight = buff.getHeight();
            //转换高度
            x = (int) (x * (realWidth * 1.0 / 600));
            y = (int) (y * (realWidth * 1.0 / 600));
            width = (int) (width * (realWidth * 1.0 / 600));
            height = (int) (height * (realWidth * 1.0 / 600));


            Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = iterator.next();
            //获取图片流
            imageInPut = ImageIO.createImageInputStream(fileInPut);
            //设置用于给定的ImageInputStream或其他Object 的输入源。
            //设置为true，则只能按升序从此输入源中读取图像和元数据 ---> 猜测 避免缓存包含与以前已经读取的图像关联的数据
            reader.setInput(imageInPut, true);

            //描述如何对流进行解码的类
            ImageReadParam param = reader.getDefaultReadParam();
            //图片裁剪
            Rectangle rectangle = new Rectangle(x, y, width, height);
            //提供一个 BufferedImage，将其用作解码像素数据的目标
            param.setSourceRegion(rectangle);

            //使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将它作为一个完整的BufferedImage 返回。
            BufferedImage bufferedImage = reader.read(0, param);
            //保存图片
            ImageIO.write(bufferedImage, "jpg", new File(newPath));
        } finally {
            if (fileInPut != null){
                fileInPut.close();
            }
            if (imageInPut != null){
                imageInPut.close();
            }
        }
    }

    public static void cutImage(String imagePath, String newPath) throws Exception {
        FileInputStream fileInPut = null;
        ImageInputStream imageInPut = null;

        try {
            //读取图片
            fileInPut = new FileInputStream(imagePath);
            //获取图片的长宽等信息
            BufferedImage buff = ImageIO.read(new File(imagePath));
            int realWidth = buff.getWidth();
            int realHeight = buff.getHeight();
            int newHeight;
            int x;
            int y;
            //转换高度
            int newWidth = 1440;
            if (realWidth >= 1440) {
                newWidth = 1440;
                x = realWidth/2 - newWidth/2;
            }else {
                newWidth = realWidth;
                x=0;
            }
            if (realHeight >= 406) {
                newHeight = 406;
                y = realHeight/2 - newHeight/2;
            }else {
                newHeight = realHeight;
                y=0;
            }
            Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = iterator.next();
            //获取图片流
            imageInPut = ImageIO.createImageInputStream(fileInPut);
            //设置用于给定的ImageInputStream或其他Object 的输入源。
            //设置为true，则只能按升序从此输入源中读取图像和元数据 ---> 猜测 避免缓存包含与以前已经读取的图像关联的数据
            reader.setInput(imageInPut, true);

            //描述如何对流进行解码的类
            ImageReadParam param = reader.getDefaultReadParam();
            //图片裁剪
            Rectangle rectangle = new Rectangle(x, y, newWidth, newHeight);
            //提供一个 BufferedImage，将其用作解码像素数据的目标
            param.setSourceRegion(rectangle);

            //使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将它作为一个完整的BufferedImage 返回。
            BufferedImage bufferedImage = reader.read(0, param);
            //保存图片
            ImageIO.write(bufferedImage, "jpg", new File(newPath));
        } finally {
            if (fileInPut != null){
                fileInPut.close();
            }
            if (imageInPut != null){
                imageInPut.close();
            }
        }
    }


//
//    public static void main(String[] args) {
//        try {
//            cutImage("E:/zhang.jpg", "F:/zhang.jpg", 200, 100 , 500, 1000);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    /**
     * 将图片格式转化为jpg格式
     * ImageIO 支持 gif->jpg,gif->png,png->gif,png->jpg
     * @param srcl
     * @param result
     * @throws Exception
     */
    public static void imageToJPG(String srcl, String result) throws Exception{
        File file = new File(srcl);
        file.canRead();
        BufferedImage src = ImageIO.read(file);
        ImageIO.write(src, "jpg", new File(result));
    }
}
