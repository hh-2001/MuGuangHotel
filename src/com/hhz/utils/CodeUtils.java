package com.hhz.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//验证码的工具类
public class CodeUtils {
    //生成图片大小
    private static int width = 80;
    private static int height = 34;
    private static int codeCount = 4;

    //验证码内容
    private static int baseX = 12;//字母起始位置
    private static int xx = 15;//偏移量
    private static int fontSize = 22;
    private static int codeY = 24;
    private static char[] codeSequence = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    //生成图片
    public static Map<String, Object> generateCode(){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gh = image.getGraphics();

        Random random = new Random();//随机生成验证码
        gh.setColor(Color.WHITE);
        gh.fillRect(0, 0, width, height);

        //创建字体
        Font font = new Font("Fixedsys", Font.BOLD, fontSize);
        gh.setFont(font);

        gh.setColor(Color.BLACK);
        gh.drawRect(0, 0, width-1, height-1);//画边框

        //随机生成干扰线
        gh.setColor(Color.GRAY);
        for(int i=0;i < 30;i++){
            int x = random.nextInt( width );
            int y = random.nextInt( height );
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            gh.drawLine(x, y, x+x1, y+y1);
        }

        StringBuffer randomCode = new StringBuffer();
        int red = 0,green=0,blue=0;

        for(int i=0;i < codeCount;i++){
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            randomCode.append(code);//保存验证码
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            gh.setColor(new Color(red, green, blue));
            gh.drawString(code, (i)*xx+baseX, codeY);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", randomCode);
        map.put("codePic", image);
        return map;
    }

//    public static void main(String[] args) throws IOException {
//        Map<String, Object> code = CodeUtils.generateCode();
//        BufferedImage image = (BufferedImage) code.get("codePic");
//        OutputStream os = new FileOutputStream("1.jpg");
//        ImageIO.write(image, "JPEG", os);
//        System.out.println(code.get("code"));
//    }

}
