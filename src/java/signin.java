/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giriraj maheshwari
 */
public class signin extends HttpServlet {

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
            
            
            //out.println("con");
            
            String em = request.getParameter("email");
            String psw = request.getParameter("pass");
            Connection con=null;
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr"); 
            //if(con!=null)
            //out.println("connection established" + con.toString());
            Statement st=con.createStatement();
            //out.println(" established");
            
          //  int x=st.executeUpdate("insert into login values('" + uname + "','" + pass +"')");
         
            String q=String.format("select count(*) from customer where email='%s' and pass='%s'",em,psw);
            
            ResultSet rs=st.executeQuery(q);
            
            //out.println(" query");
            int c=0;
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet business_op</title>");            
            out.println("</head>");
            out.println("<body >");
            if(rs.next())
            {
                c=rs.getInt(1);
                if(c!=0)
                {
            out.println("<br><br><br><h1><center>THANK YOU <br>You have been successfully logged in.</center></h1>" );
            out.println("<h4><center><a href='dresses/cartall/checkout.html'>CHECKOUT</a></center></h4>" );
            
            
                }
                else
                { 
                    out.println("<h4><center>INVALID CREDENTIALS</a></center></h4>" );
                    out.println("<h4><center><a href='signin.html'>BACK</a></center></h4>" );
                    
                }
            }
            else
            {
                    out.println("<h4><center>INVALID CREDENTIALS</a></center></h4>" );
                    out.println("<h4><center><a href='signin.html'>BACK</a></center></h4>" );
            }
            out.println("</body>");
            out.println("</html>");
           // out.println("everything is fine  ");
            
        /* */
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(signin.class.getName()).log(Level.SEVERE, null, ex);
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
