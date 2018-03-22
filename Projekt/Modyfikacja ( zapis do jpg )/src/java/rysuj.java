/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.OutputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Wykres", urlPatterns = {"/wykres"})
public class rysuj extends HttpServlet {

    private int A, B, C, X;
    private int Scale;
    int Width = 0;
    int Height = 0;
    Color KolorWykresu;
    Color KolorParaboly;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        if (request.getParameter("aParam") != null
                && request.getParameter("bParam") != null
                && request.getParameter("cParam") != null) {
            this.A = Integer.parseInt(request.getParameter("aParam"));
            this.B = Integer.parseInt(request.getParameter("bParam"));
            this.C = Integer.parseInt(request.getParameter("cParam"));
            this.X = Integer.parseInt(request.getParameter("xParam"));
            KolorWykresu = Color.decode(request.getParameter("kolorOs"));
            KolorParaboly = Color.decode(request.getParameter("kolorParam"));
            this.Scale = Integer.parseInt(request.getParameter("Scale"));
            Width = 900;
            Height = 900;
            BufferedImage ObrazKoncowy = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
            Graphics2D paint = (Graphics2D) ObrazKoncowy.getGraphics();
            generujWykres(paint, Width, Height).drawImage(ObrazKoncowy, null,0,0);
            ImageIO.write(ObrazKoncowy, "JPEG", new File("C:\\Users\\Jakub\\Desktop\\wykres.jpg"));
            ImageIO.write(ObrazKoncowy, "JPEG", response.getOutputStream());
        }
    }

    private Graphics2D generujWykres(Graphics2D g2d, int Width, int Height) {
        g2d.fillRect(0, 0, Width, Height);
        int dlugoscKreski = 3;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, Width, Height);
        int osX = (int) Width / 2;
        int osY = (int) Height / 2;
        g2d.setColor(KolorWykresu);
        g2d.setColor(KolorWykresu);
        g2d.drawLine(50, osY, osX * 2 - 50, osY);
        g2d.drawLine(osX, 50, osX, osY * 2 - 50);
        int licznik = 0;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) Scale / 3));

        for (int i = osX; i > 50; i -= Scale) {
            g2d.drawLine(i, osY - dlugoscKreski, i, osY + dlugoscKreski);
            g2d.drawString(licznik + "", i - 5, osY + dlugoscKreski + 20);
            licznik++;
        }

        licznik = 0;
        for (int i = osX; i < osX * 2 - 50; i += Scale) {
            g2d.drawLine(i, osY - dlugoscKreski, i, osY + dlugoscKreski);
            g2d.drawString(licznik + "", i - 5, osY + dlugoscKreski + 20);
            licznik++;
        }

        licznik = 0;
        for (int i = osY; i > 50; i -= Scale) {
            g2d.drawLine(osX - dlugoscKreski, i, osX + dlugoscKreski, i);
            if (licznik != 0) {
                g2d.drawString(licznik + "", osX + dlugoscKreski + 15, i + 5);
            }
            licznik++;
        }

        licznik = 0;
        for (int i = osY; i < osY * 2 - 50; i += Scale) {
            g2d.drawLine(osX - dlugoscKreski, i, osX + dlugoscKreski, i);
            if (licznik != 0) {
                g2d.drawString(licznik + "", osX + dlugoscKreski + 15, i + 5);
            }
            licznik++;
        }

        double Z_mult = (double)(licznik * 2) / (osX * 2);
        double F_mult = (double)(licznik * 2) / (osY * 2);
        g2d.setColor(KolorParaboly);
        double z = (-1) * licznik;
        for (int  i = 0; i < osX * 2; i++) {
            z += Z_mult;
            System.out.print(z+" ggggg "+Z_mult+" ggggg "+getY(z, A, B, C));
            g2d.draw(new Line2D.Double(
                    ((z + licznik) / Z_mult),
                    ((licznik + getY(z, A, B, C)) / F_mult),
                    ((z + Z_mult + licznik) / Z_mult),
                    (licznik + getY(z +Z_mult, A, B, C)) / F_mult)
            );
            System.out.println("X1 = "+Double.toString((z + licznik) / Z_mult)+ " Y1 = "+ ((licznik + getY(z, A, B, C)) / F_mult));
            System.out.println("X2 = "+Double.toString((z + Z_mult + licznik) / Z_mult)+" Y2 = "+Double.toString((licznik + getY(z +Z_mult, A, B, C)) / F_mult));
        }
        g2d.drawString("Wpisana wartość to "+X, osX+X*Scale, osY-X*X*Scale);
        return g2d;
    }

    private double getY(double x, double a, double b, double c){
        double y = (-1)*(a * x * x) - (b * x) - c;
        return y;
    }

}
