/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funkcjapac;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jakub
 */
@WebServlet(name = "funkcja", urlPatterns = {"/funkcja"})
public class funkcja extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */   
            int Xs;
            int Yk;
            int ex;
            int a;
            int b;
            int c;
            int xj,xd;
            double delta;
            a = request.getParameter("a") != null ? Integer.parseInt(request.getParameter("a") ) : -1;
            b = request.getParameter("b") != null ? Integer.parseInt(request.getParameter("b") ) : 0;
            c = request.getParameter("c") != null ? Integer.parseInt(request.getParameter("c") ) : 6;
            Xs = request.getParameter("wypadkowa") != null ? Integer.parseInt(request.getParameter("wypadkowa") ) : 0; 
            
          
              delta=b*b-4*a*c;
             if (delta<0){
                System.out.println("Brak rozwiązań.");
            }if(delta==0){
                System.out.println("Równanie ma 1 pierwiastek, x1 = " + (-b/(2*a)) );
            }if(delta>0){
                System.out.println("x1 = " + (-b-Math.sqrt(delta))/(2*a) );
                System.out.println("x2 = " + (-b+Math.sqrt(delta))/(2*a) );
            }
          
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Wykres</title>");  
            out.println("</head>");
            out.println("<body style='text-align:center;'>");
            
            out.println("<form method='post'>");
            out.print("Oś x: OD <input type='number' value='-10' name='zX' > DO <input type='number' value='11' name='dX' >");
            out.print("<div></div>");
            out.print("Oś y: OD <input type='number' value='-10' name='zY' > DO <input type='number' value='11' name='dY' >");
            out.print("<div></div><br/>");
            out.print("<div></div>");
            out.print("ax<sup>2</sup> + bx + c");
            
            out.print("<div></div>");
            out.println("a: <input type='number' name='a' value='"+a+"'>");
            out.print("<div></div>");
            out.println("b: <input type='number' name='b' value='"+b+"'>");
            out.print("<div></div>");
            out.println("c: <input type='number' name='c' value='"+c+"'>");
            out.print("<div></div>");
         
            out.print("<br/>Wybór kolorów <br/>");
            
            out.println("<input type='color'  name='tlo' value='#ffffff'> - tło");
            out.print("<div></div>");
            out.println("<input type='color'  name='linie' value='#0000ff'> - linie");
            out.print("<div></div>");
            out.println("<input type='color' name='osie' value='#000000'> - osie");
            out.print("<div><br/></div>");
            
            out.println("x: <input type='number' name='wypadkowa' value='"+Xs+"'>");
            out.println("<br><br/>Pokaz punkt x,y na wykresie");
            Yk = a * Xs * Xs + b * Xs + c;
            if ( Yk != 0 ) out.println("y = " + Yk + "<br>");
            
            out.println("<br><input type='submit' value='RYSUJ' name='submit'>");
            out.println("<form><br><br/>");
            
            int zX = Integer.parseInt(request.getParameter("zX"));
            int dX = Integer.parseInt(request.getParameter("dX"));

            int zY = Integer.parseInt(request.getParameter("zY"));
            int dY = Integer.parseInt(request.getParameter("dY"));

            String stlo = request.getParameter("tlo");
            String slinia = request.getParameter("linie");
            String sosie = request.getParameter("osie");
            
            int howX = zX+dX;
            if(zX<0) howX = (zX*-1)+dX;
            int howY = zY+dY;
            if(zY<0) howY = (zY*-1)+dY;
            float lenX = 600/howX;
            float lenY = 600/howY;
            float osX = (zX+dX)*howX/1.5f;
            float osY = (zY+dY)*howY/1.5f;  
            
            
            
            
            
            
            out.println("<canvas id='c' width='600' height='600'></canvas>");
            out.println("<script>"+"var canvas = document.getElementById('c');  "+"var ctx = canvas.getContext('2d');"
                  + "ctx.translate((canvas.width/2)-("+osX+"),canvas.height/2+("+osY+"));"+"ctx.fillStyle='"+stlo+"';"+"ctx.fillRect(-800,-400,1600,800); ctx.restore();"+
                  "ctx.beginPath();ctx.strokeStyle='"+slinia+"'; ");

            for(float i = zX; i<=dX;i+=0.005)
            {   
                float y = -1*a*(i*i)+(-1)*b*i+(-1*c);
                float w1 = y*lenY;
                float w2 = i*lenX;
                if(-1*y<dY&&-1*y>zY)
                {
                   if( i < Xs && i+0.005 >= Xs ) {
                     out.println("ctx.arc("+w2+","+w1+",2,0,Math.PI*2,true);\n ctx.fill(); ctx.moveTo("+w2+","+w1+");");
            }
                    out.println("ctx.lineTo("+w2+","+w1+");ctx.stroke();");
                }else
                {
                    out.println("ctx.moveTo("+w2+","+w1+");");
                }

            }
            for(int i = zX; i<=dX;i++)
            {
                out.println("ctx.restore();ctx.beginPath(); ctx.fillStyle='"+sosie+"';");
                out.println("ctx.font=\"12px Arial\"; ctx.fillText("+i+","+(i*lenX+2)+",-3);");    
            }
            for(int i = zY; i<=dY;i++)
            {
                out.println("ctx.font=\"12px Arial\"; ctx.fillText("+i+",2,"+(i*lenY*-1-3)+");");
            }

            out.println("ctx.restore();ctx.beginPath(); ctx.strokeStyle='"+sosie+"';"+
                            "ctx.moveTo("+(-800)+","+(0)+");" +"ctx.lineTo("+800+","+0+");" +"ctx.stroke();"+
                            "ctx.moveTo("+(0)+","+(-400)+");" +"ctx.lineTo("+0+","+400+");" +"ctx.stroke();");
            out.println("ctx.restore();");
            out.println("ctx.strokeText(\"Rysujesz wykres Y = ax2 + bx + c\",10,30);");
            out.println("ctx.strokeText("+a+",10,50);");
            out.println("ctx.strokeText(\"*\",20,50);");
            out.println("ctx.strokeText("+Xs+",30,50);");
            out.println("ctx.strokeText(\"*\",40,50);");
            out.println("ctx.strokeText("+Xs+",50,50);");
            out.println("ctx.strokeText(\"+\",60,50);");
            out.println("ctx.strokeText("+b+",70,50);");
            out.println("ctx.strokeText(\"*\",80,50);");
            out.println("ctx.strokeText("+Xs+",90,50);");
            out.println("ctx.strokeText(\"+\",100,50);");
            out.println("ctx.strokeText("+c+",110,50);");
            //ax2 + bx + c
             // Yk = a * Xs * Xs + b * Xs + c;  
             out.print("</script>");

            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}