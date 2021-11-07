package com.tanunwai.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GCDCodeServlet")
public class GCDCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		((HttpServletResponse) response).setHeader("Pragma", "No-cache");
		((HttpServletResponse) response).setHeader("Cache-Control", "No-cache");
		((HttpServletResponse) response).setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		/*String passwd = new Random().ints(0, 10).limit(4).mapToObj(num -> String.valueOf(num))
				.collect(Collectors.joining());*/
		String passwd=getRamdomString(4);
		HttpSession sessionPwd=((HttpServletRequest) request).getSession();
		sessionPwd.setAttribute("passwd", passwd);
		ImageIO.write(passwdImage(passwd), "JPG", response.getOutputStream());
	}
	/*use the BufferedImage interface to get a image for writing front end*/
	private BufferedImage passwdImage(String passwd) {
		BufferedImage bufferIma = new BufferedImage(85, 50, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferIma.getGraphics();
		g.setColor(Color.RED);
		g.setFont(new Font("Consolas", Font.BOLD, 32));
		/*
		Graphics2D gcd2D=(Graphics2D)g;
		AffineTransform affinetran=new AffineTransform();
		float scaleSize=new Random().nextFloat()*0.8f;
		if(scaleSize>1f) {
			scaleSize=1f;
		}
		affinetran.scale(scaleSize, scaleSize);
		gcd2D.setTransform(affinetran);
		*/
		g.drawString(passwd, 7, 35);
		g.dispose();
		return bufferIma;
	}
	/*set the method to get random 4 words*/
	private static String getRamdomString(int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int num = rand.nextInt(3);//get 0~2
            long result = 0;
            switch (num) {
                case 0://get A~Z
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1://get a~z
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2://get 0~9
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }
}