package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.lang.String;

public final class usuarios_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("formulario:campoBoton", beans.Formularios.class, "campoBoton", new Class[] {java.lang.String.class, java.lang.String.class, java.lang.String.class});
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/header.jsp");
    _jspx_dependants.add("/menu.jsp");
    _jspx_dependants.add("/WEB-INF/tlds/formulario.tld");
    _jspx_dependants.add("/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link href=\"css/estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <script src=\"js/loadJSON.js\" type=\"text/javascript\"></script>\r\n");
      out.write("        <script src=\"js/scriptsUsuarios.js\" type=\"text/javascript\"></script>\r\n");
      out.write("        <link href=\"css/tablas.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <title>JSP Usuarios</title>\r\n");
      out.write("        \r\n");
      out.write("    </head>\r\n");
      out.write("    <body onload=\"initUsuarios()\">\r\n");
      out.write("        <div id=\"wrapper\">\r\n");
      out.write("            <header id=\"header\">\r\n");
      out.write("                 ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link href=\"css/estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <script src=\"js/scriptCompruebaLogin.js\" type=\"text/javascript\"></script>\r\n");
      out.write("         ");

             String x = request.getRequestURL().toString(); 
             System.err.println(x);
              if (request.getSession(true).getAttribute("usuario") == null  ) {
                request.getRequestDispatcher("errorIngreso.jsp").forward(request, response);
            }
        
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div>\r\n");
      out.write("        <h3>EIF209 Programacion 4</h3>\r\n");
      out.write("        <h3>Proyecto#1 Formacion de grupos</h3>\r\n");
      out.write("        <h3>1er ciclo 2019</h3>     \r\n");
      out.write("    </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write(" \r\n");
      out.write("                 ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"js/scriptCompruebaLogin.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link href=\"css/estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <title>Menu</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div id=\"wrapper\">\r\n");
      out.write("            <div id=\"header\">\r\n");
      out.write("                <table>\r\n");
      out.write("                    <tr>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            <form action=\"formacion\" method=\"POST\">\r\n");
      out.write("                                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formulario:campoBoton(\"Formación de grupos\",\"botonFormacion\",\"0\")}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_0));
      out.write("\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </td>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            <form action=\"consulta\" method=\"POST\">\r\n");
      out.write("                                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formulario:campoBoton(\"Consulta de grupos\",\"botonConsulta\",\"0\")}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_0));
      out.write("\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </td>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            <form action=\"usuarios\" method=\"POST\">\r\n");
      out.write("                                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formulario:campoBoton(\"Estudiantes\",\"botonUsuario\",\"0\")}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_0));
      out.write("\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </td>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            <form action=\"cambiarclave\" method=\"POST\">\r\n");
      out.write("                                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formulario:campoBoton(\"Cambiar clave\",\"botonCambiaClave\",\"0\")}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_0));
      out.write("\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </td>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            <form action=\"salir\" method=\"POST\">\r\n");
      out.write("                                 ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formulario:campoBoton(\"Cerrar sesión\",\"botonSalir\",\"0\")}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_0));
      out.write("\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </td>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                </table>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>    \r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("            </header>\r\n");
      out.write("            <div id=\"contents\">\r\n");
      out.write("                <form id=\"ingreso\">\r\n");
      out.write("                    <table id=\"tablaUsuarios\" class=\"tablaDatos\">\r\n");
      out.write("                        <thead>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td id=\"OrdenaSecuencia\" onclick=\"ordena()\"># Grupo</td>\r\n");
      out.write("                                <td id=\"OrdenaGrupo\" onclick=\"ordena()\">Nombre del Grupo</td>\r\n");
      out.write("                                <td id=\"OrdenaNRC\" onclick=\"ordena()\">NRC</td>\r\n");
      out.write("                                <td id=\"OrdenaId\" onclick=\"ordena()\">Identificación</td>\r\n");
      out.write("                                <td id=\"OrdenaApelligos\" onclick=\"ordena()\" >Apellidos</td>\r\n");
      out.write("                                <td id=\"OrdenaNombre\" onclick=\"ordena()\">Nombre</td>\r\n");
      out.write("                                <td>Activo</td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                        </thead>\r\n");
      out.write("                        <tbody id=\"datosTablaUsuarios\"></tbody>\r\n");
      out.write("                    </table>\r\n");
      out.write("                    <input type=\"hidden\" id=\"control\" name=\"control\" value=\"1\"/>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<footer>\r\n");
      out.write("    <link href=\"css/estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <div>\r\n");
      out.write("        <h3>Programacion 4, 2019</h3>\r\n");
      out.write("    </div>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
