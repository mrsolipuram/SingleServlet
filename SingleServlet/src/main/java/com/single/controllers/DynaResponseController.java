/**
 * It is a framework class. This is similar like DispatchController
 * The different is if user extends this class he can send the 
 * response using return type of the controller class method.
 * It can be configured using annotation. And this class can
 * validate the date before calling the controller class method.
 * 
 */
package com.single.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.BeanErrors;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.single.controllers.SResponse.ResType;
import com.single.validator.PerformValidations;
import com.single.validator.SValidationRules;

/**
 * @author madhava
 * 
 */
public class DynaResponseController implements BaseController {

	private SValidationRules validationRules;

	public SValidationRules getValidationRules() {
		return validationRules;
	}

	public void setValidationRules(SValidationRules validationRules) {
		this.validationRules = validationRules;
	}

	public final void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		@SuppressWarnings("rawtypes")
		Class classReq = this.getClass();
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[] { HttpServletRequest.class,
				HttpServletResponse.class };
		// get the methodName
		String methodName = req.getParameter("parameter");
		try {
			if (methodName != null) {
				@SuppressWarnings("unchecked")
				Method method = classReq.getMethod(methodName, types);
				// create the object array for passing arguments for controller
				// method
				Object[] args = new Object[] { req, resp };
				SResponse sRes = method.getAnnotation(SResponse.class);
				if (calValidation(method, req, resp, sRes)) {
					// call the controller method
					Object result = method.invoke(this, args);
					sendResponse(req, resp, sRes, result);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * It will send the response based on SResponse annotation
	 * 
	 * @param req
	 * @param resp
	 * @param a
	 * @param result
	 * @throws IOException
	 * @throws ServletException
	 */
	public final void sendResponse(HttpServletRequest req,
			HttpServletResponse resp, SResponse a, Object result)
			throws IOException, ServletException {
		if (a.ResType().equals(ResType.SEND_REDIRECT)) {
			sendRedirect(req, resp, result.toString());
		} else if (a.ResType().equals(ResType.FORWARD)) {
			sendForward(req, resp, result.toString());
		} else if (a.ResType().equals(ResType.JSON))
			sendJSON(req, resp, result);
		else
			System.out.println("no return type");
	}

	/**
	 * validation method for controller if it return true then only it will call
	 * the controller method It will automatically call the validation method by
	 * using annotaion tags and reflection
	 * 
	 * 
	 * @param controlMethod
	 * @param req
	 * @param resp
	 * @param sres
	 * @return
	 */
	public final boolean calValidation(Method controlMethod,
			HttpServletRequest req, HttpServletResponse resp, SResponse sres) {
		SValidator sValidator = controlMethod.getAnnotation(SValidator.class);
		if (sValidator == null)
			return true;
		else {
			// error objects is created to store error message inthis object
			BeanErrors errors = new BeanErrors();
			errors.setsProperties(SWebUtil.getProperties());
			try {
				// validation method must contains two parameters with the
				// following types
				Class[] types = new Class[] { HttpServletRequest.class,
						BeanErrors.class };
				// creating validationClass it may be controller class or it may
				// be different validation class
				Class validatorClass = null;
				// assigning validation object
				Object validatorObject = null;
				// creating arguments for calling validation method
				Object[] args = new Object[] { req, errors };
				// json based validations based on form name

				if (!sValidator.FORM_ID().equals("")) {
					if (validationRules == null) {
						getValidationRulesFmCtxt(req);
					}
					PerformValidations pv = new PerformValidations();
					pv.performValidations(req, errors, sValidator.FORM_ID(),
							validationRules);
				}
				// it will execute if user doesn't specify the validation calss
				// in SValidator annotaion it will treat as same controller
				// class
				else {
					if (sValidator.CLASS_NAME().equals("SameClass")) {
						validatorClass = this.getClass();
						validatorObject = this;
					} else {
					// it will loads and create validationn object for each
					// request
						validatorClass = Class.forName(sValidator.CLASS_NAME());
						validatorObject = validatorClass.newInstance();
					}
						Method method = validatorClass.getMethod(
								sValidator.METHOD_NAME(), types);
					if (method == null) {
						System.out
								.println("validation method is not found: or signature is not correct");
					}
					// invoking the validation method
					method.invoke(validatorObject, args);
					// if validation method adds any validation messages it will
				}
				// redirect to validation page
				// the error messages are sent to client based on SResponse of
				// controller method
				Map<String, Object> error = errors.getErrors();
				if (error.size() > 0) {
					if (sres.ResType().equals(ResType.SEND_REDIRECT)) {
						req.getSession().setAttribute("errors", error);
						sendRedirect(req, resp, "/index.jsp");
					} else if (sres.ResType().equals(ResType.FORWARD)) {
						req.setAttribute("errors", error);
						sendForward(req, resp, "/index.jsp");
					} else if (sres.ResType().equals(ResType.JSON)) {
						errors.addError("error", "true");
						sendJSON(req, resp, error);
					} else
						System.out.println("no return type");
					return false;
				} else
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	private void getValidationRulesFmCtxt(HttpServletRequest req) {
		setValidationRules((SValidationRules) WebApplicationContextUtils
				.getWebApplicationContext(SWebUtil.getServletContext())
				.getBean("validationRules"));

	}

	/**
	 * redirecting the request to specfic url
	 * 
	 * @param req
	 * @param resp
	 * @param result
	 * @throws IOException
	 */
	public final void sendRedirect(HttpServletRequest req,
			HttpServletResponse resp, String result) throws IOException {
		resp.sendRedirect(req.getContextPath() + result);
	}

	/**
	 * forwarding the request to another request
	 * 
	 * @param req
	 * @param resp
	 * @param result
	 * @throws IOException
	 * @throws ServletException
	 */
	public final void sendForward(HttpServletRequest req,
			HttpServletResponse resp, String result) throws IOException,
			ServletException {
		RequestDispatcher rd = req.getRequestDispatcher(result);
		rd.forward(req, resp);
	}

	/**
	 * Sending the json date using google Gson library
	 * 
	 * @param req
	 * @param resp
	 * @param result
	 * @throws IOException
	 */
	public final void sendJSON(HttpServletRequest req,
			HttpServletResponse resp, Object result) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(result);
		PrintWriter out = resp.getWriter();
		out.write(json);
		out.flush();
		out.close();

	}

}
