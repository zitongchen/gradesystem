package team.wuming.common.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 
 * @author Tony 图片验证码对象的创建
 */
public class NumberCode {

	private int w=95;//设置 图片的宽度
	private int h=35;//设置验证码的高度
	private Random r=new Random();///获取随机数
	
	private String[] fontNames={"宋体","华文楷体","黑体","微软雅黑"};//添加字体的类型到一个数组
	private char[] ch="0147852369".toCharArray();///传入数字
	private Color bgColor=new Color(170,170,255);///设置验证码的的底色（可再改进）
	private String text;
	
	//随机获取颜色的方法RGB
	private Color randomColor(){
		int red=r.nextInt(150);
		int green=r.nextInt(150);
		int blue=r.nextInt(150);
		return new Color(red,green,blue);
	}
	
	//随机获取字体格式的方法
	private Font randomFont(){
		int index=r.nextInt(fontNames.length);
		String fontName=fontNames[index];
		int style=r.nextInt(4);//字体的粗细
		int size=r.nextInt(5)+24;//字体的大少
		return new Font(fontName, style, size);//设置字体的类型，字体的style（粗细），字体的大小，
	}
	
	private void drawLine(BufferedImage image){
		int num=3;
		Graphics2D g2=(Graphics2D) image.getGraphics();//获取画板
		//画三条线
		for(int i=0;i<num;i++){
			int x1=r.nextInt(w);
			int y1=r.nextInt(h);
			int x2=r.nextInt(w);
			int y2=r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));//笔画的粗细
			g2.setColor(Color.BLUE);//线的颜色
			g2.drawLine(x1, y1, x2, y2);//线的位置
		}
	}
	//获取随机的字符方法
	private char randomChar(){
		int index=r.nextInt(ch.length);
		return ch[index];
	}
	
	//创建图片缓冲的方法
	private BufferedImage createImage(){
		BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=(Graphics2D) image.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);//设置画板的大小，跟缓冲区的大少一致
		return image;
	}
	
	//获取图片缓冲的方法
	public BufferedImage getImage(){
		BufferedImage image=createImage();
		Graphics2D g2=(Graphics2D) image.getGraphics();
		String num1=randomChar()+"";
		String num2=randomChar()+"";
		int total=Integer.valueOf(num1)+Integer.valueOf(num2);
		
		//设置各个符号的位置，颜色，格式
		g2.setColor(randomColor());
		g2.setFont(randomFont());
		g2.drawString(num1, 10+3, 25);
		
		g2.setColor(randomColor());
		g2.setFont(randomFont());
		g2.drawString("+", 2*15+3, 20);
		
		g2.setColor(randomColor());
		g2.setFont(randomFont());
		g2.drawString(num2, 3*15+3, 23);
		
		g2.setColor(randomColor());
		g2.setFont(randomFont());
		g2.drawString("=", 4*15+3, 30);
		
		g2.setColor(randomColor());
		g2.setFont(randomFont());
		g2.drawString("?", 5*15+3, 25);
		this.text=total+"";
		drawLine(image);//给缓冲区添加干扰线
		return image;
	}
	//获取相加的结果
	public String getTest(){
		return text;
	}
	//图片缓冲的输出
	public static void output(BufferedImage image,OutputStream out) throws IOException{
		ImageIO.write(image, "JPG", out);
	}
}
