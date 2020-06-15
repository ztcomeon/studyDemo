package com.example.studyDemo.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 图片处理工具类
 *
 * @Author wangyu
 * @Date 2018/11/12 17:17
 * @Version 1.0
 */
@SuppressWarnings("restriction")
public class ImageUtils {
  public static final Logger LOG = LoggerFactory.getLogger(ImageUtils.class);

  /**
   * 图片灰度处理 MultipartFile multipartFile,
   */
  public static String toGray(String path, String fileName, String filePrefix) {
    // 图片保存地址
    File file = new File(UploadUtils.getPath());
    File newFile = new File(UploadUtils.getPath() + File.separator + filePrefix);
    if (!file.exists()) {
      file.mkdirs();
    } else {
      if (!newFile.exists()) {
        newFile.mkdirs();
      }
    }

    BufferedImage image = null;
    try {
      File files = new File(path);
      image = ImageIO.read(files);

      int width = image.getWidth();
      int height = image.getHeight();

      BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          int rgb = image.getRGB(i, j);
          grayImage.setRGB(i, j, rgb);
        }
      }

      String src = UploadUtils.getPath() + File.separator + filePrefix + File.separator
          + getImageSuffix(fileName);
      File file1 = new File(src);
      ImageIO.write(grayImage, "jpg", file1);
      return src;
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  /**
   * 转为base64编码
   */
  public static String toBase64(String imgFile) {
    // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    InputStream in = null;
    byte[] data = null;
    // 读取图片字节数组
    try {
      in = new FileInputStream(imgFile);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new String(Base64.encodeBase64(data));
  }

  /**
   * 将base64编码转为图片 并保存
   */
  public static String parseBase64ToImgAndSave(String img_base64, String filePrefix) {
    // 图像数据为空
    if ("".equals(img_base64)) {
      throw new IllegalArgumentException("图片编码不能为空");
    }
    BASE64Decoder decoder = new BASE64Decoder();
    String filePath = "";
    try {
      byte[] b = decoder.decodeBuffer(img_base64);
      for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {// 调整异常数据
          b[i] += 256;
        }
      }
      File file = new File(UploadUtils.getPath());
      String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
      File newFile = new File(UploadUtils.getPath() + "/" + filePrefix + "/" + folderName);
      if (!file.exists()) {
        file.mkdirs();
      } else {
        if (!newFile.exists()) {
          newFile.mkdirs();
        }
      }
      // 生成jpeg图片
      String path = UploadUtils.getPath() + "/" + filePrefix + "/" + folderName + "/";
      String newFileName = UUID.randomUUID() + ".jpeg";// 生成jpg图片
      String imgFilePath = path + newFileName;
      filePath = filePrefix + "/" + folderName + "/" + newFileName;
      OutputStream out = new FileOutputStream(imgFilePath);
      out.write(b);
      out.flush();
      out.close();
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
    return filePath;
  }

  /**
   * 保存图片
   */
  public static String saveImg(MultipartFile multipartFile, String fileName, String filePrefix) {
    // 图片保存地址
    File file = new File(UploadUtils.getPath());
    File newFile = new File(UploadUtils.getPath() + File.separator + filePrefix);
    if (!file.exists()) {
      file.mkdirs();
    } else {
      if (!newFile.exists()) {
        newFile.mkdirs();
      }
    }

    String path = UploadUtils.getPath() + File.separator + filePrefix + File.separator
        + getImageSuffix(fileName);
    BufferedOutputStream bos = null;
    try {
      FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
      bos = new BufferedOutputStream(new FileOutputStream(path));
      byte[] bs = new byte[1024];
      int len;
      while ((len = fileInputStream.read(bs)) != -1) {
        bos.write(bs, 0, len);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bos != null) {
          bos.flush();
          bos.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return path;
  }

  /**
   * 保存用户个性头像
   */
  public static String saveHeadImg(MultipartFile multipartFile, String fileName,
                                   String filePrefix) {
    String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
    File file = new File(UploadUtils.getPath());
    File newFile = new File(UploadUtils.getPath() + "/" + filePrefix + "/" + folderName);
    if (!file.exists()) {
      file.mkdirs();
    } else {
      if (!newFile.exists()) {
        newFile.mkdirs();
      }
    }
    String newFileName = UploadUtils.reNameFile(fileName);
    String filePath = filePrefix + "/" + folderName + "/" + newFileName;
    BufferedOutputStream bos = null;
    try {
      FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
      bos = new BufferedOutputStream(new FileOutputStream(UploadUtils.getPath() + "/" + filePath));

      byte[] bs = new byte[1024];
      int len;
      while ((len = fileInputStream.read(bs)) != -1) {
        bos.write(bs, 0, len);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bos != null) {
          bos.flush();
          bos.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return filePath;
  }

  /**
   * 设置默认图片后缀
   */
  public static String getImageSuffix(String fileName) {
    String newFileName = fileName + ".jpeg";
    return newFileName;
  }

  /**
   * 删除指定图片
   *
   * @param filePrefix 文件夹名
   * @param fileName 文件名
   */
  public static void deleteImg(String filePrefix, String fileName) {
    File file = new File(UploadUtils.getPath());
    File newFile = new File(UploadUtils.getPath() + File.separator + filePrefix);
    if (!file.exists()) {
      return;
    } else {
      if (!newFile.exists()) {
        return;
      }
    }
    // 删除
    File[] files = newFile.listFiles();
    if (files != null) {
      for (File f : files) {
        if (f.getName().equals(fileName)) {
          f.delete();
        }
      }
    }
  }

  public static String download(String imgUrl, String filePrefix) {
    try {
      String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
      // 图片保存地址
      File file = new File(UploadUtils.getPath());
      File newFile = new File(UploadUtils.getPath() + "/" + filePrefix + "/" + folderName);
      String newFileName = UUID.randomUUID() + ".jpeg";// 生成jpg图片
      String savePath =
          UploadUtils.getPath() + "/" + filePrefix + "/" + folderName + "/" + newFileName;
      if (!file.exists()) {
        file.mkdirs();
      } else {
        if (!newFile.exists()) {
          newFile.mkdirs();
        }
      }
      URL url = new URL(imgUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      DataInputStream in = new DataInputStream(connection.getInputStream());
      DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
      /* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
      byte[] buffer = new byte[4096];
      int count = 0;
      /* 将输入流以字节的形式读取并写入buffer中 */
      while ((count = in.read(buffer)) > 0) {
        out.write(buffer, 0, count);
      }
      out.close();
      in.close();
      connection.disconnect();
      // 返回内容是保存后的完整的URL
      return "/static/" + filePrefix + "/" + folderName + "/" + newFileName;
    } catch (Exception e) {
      return null;
    }
  }

  public static String download(InputStream inputStream, String filePrefix) {
    try {
      // 图片保存地址
      String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
      File newFile = new File(UploadUtils.getPath() + "/" + filePrefix + "/" + folderName);
      String newFileName = UUID.randomUUID() + ".jpeg";// 生成jpg图片
      String savePath =
          UploadUtils.getPath() + "/" + filePrefix + "/" + folderName + "/" + newFileName;
      if (!newFile.exists()) {
        newFile.mkdirs();
      }
      DataInputStream in = new DataInputStream(inputStream);
      DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
      /* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
      byte[] buffer = new byte[1024*1024];
      int count = 0;
      /* 将输入流以字节的形式读取并写入buffer中 */
      while ((count = in.read(buffer)) > 0) {
        out.write(buffer, 0, count);
      }
      out.close();
      // 返回内容是保存后的完整的URL
      return "/static/" + filePrefix + "/" + folderName + "/" + newFileName;
    } catch (Exception e) {
      return null;
    }
  }
}
