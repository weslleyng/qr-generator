package br.irg.itbam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.GenericArrayType;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

public class Main {

	public static void main(String[] args) {

		// File file = new File(System.getProperty("user.home"));

		Map<Integer, Casa> referencia = new HashMap<Integer, Casa>();
		referencia.put(1, getCasaMap(14, 1));
		referencia.put(2, getCasaMap(13, 1));
		referencia.put(3, getCasaMap(34, 1));
		referencia.put(4, getCasaMap(24, 1));
		referencia.put(5, getCasaMap(24, 1));
		referencia.put(6, getCasaMap(7, 1));
		referencia.put(7, getCasaMap(21, 1));
		referencia.put(8, getCasaMap(20, 1));
		referencia.put(9, getCasaMap(20, 1));
		referencia.put(10, getCasaMap(20, 1));
		referencia.put(11, getCasaMap(19, 1));
		referencia.put(12, getCasaMap(8, 1));
		referencia.put(13, getCasaMap(8, 1));
		referencia.put(14, getCasaMap(8, 1));
		referencia.put(15, getCasaMap(8, 1));
		referencia.put(16, getCasaMap(14, 6));
		referencia.put(17, getCasaMap(14, 6));
		referencia.put(18, getCasaMap(14, 6));
		referencia.put(19, getCasaMap(4, 6));

		try {

			int totalRua = 19;
			
			for (int rua = 1; rua <= totalRua; rua++) {

				String numeroRua;
				if (rua < 10)
					numeroRua = "0";
				else
					numeroRua = "";

				String labelRua = numeroRua + rua;

				Casa casaMap = referencia.get(rua);

				for (int casa = 1; casa <= casaMap.getQtd(); casa++) {

					String numeroCasa = "";
					if (casa < 10) {
						numeroCasa = "0";
					} else {
						numeroCasa = "";
					}

					String labelCasa = numeroCasa + casa;

					int nivelQtd = casaMap.getQtdNivel();
					for (int nivel = 1; nivel <= nivelQtd; nivel++) {
						String label = "R" + labelRua + "C" + labelCasa + "N" + nivel;
						ByteArrayOutputStream stream = QRCode.from(label).withSize(600, 550).to(ImageType.JPG).stream();
						File file = new File("qr-code.jpg");
						FileOutputStream outputStream = new FileOutputStream(file);
						outputStream.write(stream.toByteArray());
						outputStream.close();
						generate(file, label);
					}

				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static Casa getCasaMap(Integer casa, Integer nivel) {
		
		Casa c = new Casa();
		c.setQtd(casa);
		c.setQtdNivel(nivel);
		
		return c;
	}

	public static void generate(File file, String filename) throws IOException {
		final BufferedImage image = ImageIO.read(file);
		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(35f));
		g.setColor(Color.BLACK);
		g.drawString("MARSHALL + " + filename, 100, 50);
		g.dispose();
		System.out.println("generated=" + file.toString());
		ImageIO.write(image, "png", new File(filename + ".png"));
	}

	
}
