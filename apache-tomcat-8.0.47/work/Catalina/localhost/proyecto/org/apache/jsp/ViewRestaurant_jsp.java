/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.47
 * Generated at: 2018-05-11 13:02:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import restaurant.*;
import java.util.*;

public final class ViewRestaurant_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("restaurant");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"Error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset = \"UTF-8\">\n");
      out.write("\t\t<title>Carta</title>\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" integrity=\"sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB\" crossorigin=\"anonymous\">\n");
      out.write("\t\t<style type=\"text/css\"> \n");
      out.write("\t\t\t.ctr { \n");
      out.write("\t\t\t\ttext-align: center;\n");
      out.write("\t\t\t\tfont-size: 20px;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t.img-css{\n");
      out.write("\t\t\t\tmax-width: 50%;\n");
      out.write("\t\t\t\tmargin-left: 27%;\n");
      out.write("\t\t\t\tborder-radius: 30px;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t.centre{\t\n");
      out.write("\t\t\t\tpadding: -25%;\n");
      out.write("\t\t\t}\t\n");
      out.write("\t\t</style>\t\t\t \t\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t\t");
 ArrayList<Plato> cart = (ArrayList<Plato>) request.getAttribute("cart"); 
      out.write('\n');
      out.write('	');
      out.write('	');
 User user = (User) session.getAttribute("user"); 
      out.write('\n');
      out.write('	');
      out.write('	');
 String id_rest_str = (String) request.getAttribute("id"); 
      out.write('\n');
      out.write('	');
      out.write('	');
 String err = (String) request.getAttribute("err"); 
      out.write("\n");
      out.write("\t\t\t");
 if("1".equals(err)){ 
      out.write("\n");
      out.write("\t\t\t<p> Error al añadir el plato, ya existe un plato con ese nombre </p>\n");
      out.write("\t\t\t");
}
      out.write("\n");
      out.write("\t\t\t<img src=\"chameli_dallas_menu.png\" class=\"img-rounded img-css\" height = \"300px\" width =\"70%\">\n");
      out.write("\t\t\t<table class=\"table-hover ctr table\">\n");
      out.write("\t\t\t\t<thead class=\"thead-dark\">\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th> Plato </th>\n");
      out.write("\t\t\t\t\t\t<th> Descripción</th>\n");
      out.write("\t\t\t\t\t\t<th> Precio </th>\n");
      out.write("\t\t\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t\t\t<th> <form action = \"AddPlate.jsp\">\n");
      out.write("\t\t\t\t\t\t\t\t<input type = \"hidden\" name = \"idrest\" value = \"");
      out.print(id_rest_str);
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-success\" name = \"addplate\" value = \"Añadir plato\" >\n");
      out.write("\t\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t</th>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</thead>\n");
      out.write("\t\t\t\t<tbody>\n");
      out.write("\t\t\t\t\t");
 for(int i = 0; i< cart.size(); i++){ 
      out.write("\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print( cart.get(i).getNamePlate() );
      out.write(" </td>\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print( cart.get(i).getDescripcion() );
      out.write(" </td>\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print( cart.get(i).getPrecio() );
      out.write(" € </td>\n");
      out.write("\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t");
 if(user.getIdType() == 2){ 
      out.write("\n");
      out.write("\t\t\t\t\t\t<form action = \"changeprice\">\n");
      out.write("\t\t\t\t\t\t\t<input type = \"number\" name = \"newprice\" min = \"0\" max = \"9999.99\" step =\"0.01\" required>\n");
      out.write("\t\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-warning\" name = \"changeprice\" value = \"Modificar Precio\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name = \"idplato\" value = \"");
      out.print( cart.get(i).getIdPlato() );
      out.write("\">\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t<form action = \"deleteplate\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-danger\" name = \"deleteplate\" value = \"Eliminar plato\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name = \"idplatodelete\" value = \"");
      out.print( cart.get(i).getIdPlato() );
      out.write("\">\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t");
}
      out.write("\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t</tbody>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
