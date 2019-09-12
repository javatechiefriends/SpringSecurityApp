package com.app.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.User;
import com.app.service.UserService;
import com.app.utils.RecaptchaUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RecaptchaUtil utils;
	
	@GetMapping({"/signup"})
	public String signup() {
		
		return "signup";
	}
	
	@GetMapping({"/","/login"})
	public String login() {
		
		return "login";
	}
	
	@PostMapping("/save")
	public String saveUserData(@ModelAttribute User user,ModelMap model) {
		
		System.out.println(user);
		
		User usr=userService.getUserByEmail(user.getEmail());
		if(usr!=null) {
			model.addAttribute("msg", "Email already exits");
			model.addAttribute("user", user);
			return "signup";
		}else {
			Long userid=userService.saveUserData(user);
			System.out.println("user register successfully & userid is :"+userid);
			model.addAttribute("user", new User());
			model.addAttribute("msg", "user register successfully & userid is :"+userid);
			return "login";
		}
	}

	@GetMapping("/dashboard")
	public String getAllUsers(ModelMap model,Principal principal){
		
		String loginUser=principal.getName();
		
		//utils.generateRecap();
		
		
		model.addAttribute("email", loginUser);
		
		return "dashboard";
	}
	
	@GetMapping("/recaptchaImage.jpg")
	public void repcatcha(HttpServletRequest req, HttpServletResponse response, HttpSession session)
			throws IOException {
		// set mime type as jpg image
		response.setContentType("image/jpeg,image/jpg");
		ServletOutputStream out = response.getOutputStream();

		BufferedImage image = new BufferedImage(100, 40, BufferedImage.TYPE_BYTE_INDEXED);

		Graphics2D graphics = image.createGraphics();

		// Set back ground of the generated image to white
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 200, 40);

		// set gradient font of text to be converted to image
		GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLUE, 20, 10, Color.LIGHT_GRAY, true);
		graphics.setPaint(gradientPaint);
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		graphics.setFont(font);

		// write 'Hello World!' string in the image

		String recaptha = utils.generateRecap();

		session.setAttribute("sesReCaptcha", recaptha);
		System.out.println("session create...");

		graphics.drawString(recaptha, 5, 30);

		// release resources used by graphics context
		graphics.dispose();

		// encode the image as a JPEG data stream and write the same to servlet
		// output stream
		// sJPEGCodec.createJPEGEncoder(out).encode(image);

		// close the stream

		ImageIO.write(image, "png", out);

		out.close();
	}
	
	/*
	 * Bar code generate example
	 */
	@RequestMapping("/qr_image.jpg")
	private void createQRImage(HttpServletResponse response) throws WriterException, IOException {

		String qrCodeText = "Name : Mahendra \nDesgination : Java Developer";
		String filePath = "png";
		int size = 125;
		String fileType = "png";
				
		ServletOutputStream out = response.getOutputStream();

		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, out);
	}


}
