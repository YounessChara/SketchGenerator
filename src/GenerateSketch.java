import java.io.*;
import java.util.*;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;

public class GenerateSketch {
	
	static File tmplt = new File( "./sketchs/sketch-standard-tmplt.txt");
	public static void main(String[] args) throws Exception {
		String className="GoodNews"; 
	 
		String packeageName ="";
		String parsing="T,F,P,G[F][F]";
		String outdir="./src";
		VelocityContext vc =new VelocityContext();
		vc.put("className", className);
		vc.put("packageName", packeageName); 
		int i = 0;
		for(String v:parsing.split(",")) { 
			Object conf = getVariableConfig(v);
			System.out.println("V"+i+"--"+v+"-->"+conf);
			vc.put("v"+i, conf);
			i++;
		}
		File code = new File (outdir+"/"+packeageName.replace(".", "/")+"/"+className+".java");
		PrintWriter writer=new PrintWriter(code);
		getTemplate(tmplt).merge(vc, writer);
		writer.close();
		
	}


	private static Object getVariableConfig(String v) {
		System.out.println("getVariableConfig::"+v);
		TreeMap<String, Object> conf =new TreeMap<String, Object>();
		if(v.startsWith("l:")) {
			conf.put("type", "long");
			conf.put("scan", "in.nextLong()");
			v=v.substring(2);
		} else if(v.startsWith("d:")) {
			conf.put("type", "double");
			conf.put("scan", "in.nextDouble()");
			v=v.substring(2);
		} else if(v.startsWith("s:")) {
			conf.put("type", "String");
			conf.put("scan", "in.next()");
			v=v.substring(2);
		} else if(v.startsWith("i:")) {
			conf.put("type", "int");
			conf.put("scan", "in.nextInt()");
			v=v.substring(2);
		} else if(v.matches("[A-Za-z0-9]+:")) {
			int idp = v.indexOf(":");
			conf.put("type", v.substring(idp));
			conf.put("scan", "new "+conf.get("type")+"()");
			v=v.substring(2);
		} else {
			conf.put("type", "int");
			conf.put("scan", "in.nextInt()"); 
		}
		String variable="";
		while(v.matches("[A-Za-z0-9]+.*")) {
			System.out.println(">>"+v+" match[A-z0-9]");
			variable+=v.substring(0, 1);
			v=v.substring(1);
		}
		conf.put("variable", variable);

		conf.put("loop", "");
		if(v.contains("[")) {
			String lo ="";
			String lc ="";
			String t = (String) conf.get("type");
			String scan =" new "+t;
			int index = 0;
			for(String s:v.split("\\]")) {
				index++;
				t+="[]";
				String lv = s.replaceAll(".*\\[", ""); 
				scan+="["+lv+"]";
				lo+= "for(int i"+index+"=0; i"+index +"<"+lv+"; i"+index+"++) {";
				
				lc="}"+lc;
			}
			conf.put("type", t);
			conf.put("scan", scan);
			conf.put("loop", lo + lc);
		}  
		
			conf.put("instance", conf.get("type")+" "+conf.get("variable")+" = "+conf.get("scan")+";");
		
		return conf;
	}


	public static Template getTemplateFromReader(Reader reader) throws Exception {
		  RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();   
	        SimpleNode node = runtimeServices.parse(reader, "Template"+new Date().getTime()+"--"+Math.random());
	        Template template = new Template();
	        template.setRuntimeServices(runtimeServices);
	        template.setData(node);
	        template.initDocument();
	        return template;
	}
	
	public static Template getTemplate(String tempExp) throws Exception {
	StringReader sr =new StringReader(tempExp);
	return getTemplateFromReader(sr);
	}
	static public Template getTemplate(File templateFile) throws Exception {
		FileInputStream fis = new FileInputStream(templateFile);
		InputStreamReader isr  =new InputStreamReader(fis, "UTF8");  
		return getTemplateFromReader(isr);
		}
	
	
}
