import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.sql.*;
import java.lang.ClassNotFoundException;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	long serialVersionUID = 1L;
	String UPLOAD_DIRECTORY = "D:\\PDF";
	int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	String pdfname = "";
	String filename = "";
	int sem = 0;

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{

		// Check if the request contains a multipart/form-data
		if (!ServletFileUpload.isMultipartContent(request)) 
		{
			// If not, we stop here
			response.getWriter().println("Error: Form must have enctype=multipart/form-data.");
			response.flushBuffer();
			return;
		}

		// Configures the upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);

		HttpSession session=request.getSession(false);  
		String user_name=(String)session.getAttribute("user_name");

		RequestDispatcher dispatcher = null;

		// Constructs the directory path to store upload file
		File uploadDir = new File(UPLOAD_DIRECTORY);
		if (!uploadDir.exists()) 
		{
			uploadDir.mkdir();
		}

		try 
		{
			// Parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// Iterates over form's fields
				for (FileItem item : formItems) {
					// Processes only fields that are not form fields
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
						File storeFile = new File(filePath);
						String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						// Saves the file on disk
						item.write(storeFile);
						if (fileType.equals("pdf")) {
							pdfname=fileName;
							//response.getWriter().println("File " + pdfname + " uploaded successfully.1");
						}	 else if (fileType.equals("txt") || fileType.equals("xlsx") || fileType.equals("xls")) {
							filename=fileName;
							//response.getWriter().println("File " + filename + " uploaded successfully.2");
						}
					}
				}
			}
		} 
		catch (Exception ex) 
		{
			response.getWriter().println("Error: " + ex.getMessage());
		}
		String rollnumber;
		int general_male, general_female, general_male_pass, general_female_pass, general_male_pass_dis, general_female_pass_dis, general_female_pass_first, general_male_pass_first, general_female_pass_second, general_male_pass_second;
		int obc_male, obc_female, obc_female_pass, obc_male_pass,obc_male_pass_dis,obc_female_pass_dis, obc_male_pass_first, obc_female_pass_first, obc_male_pass_second, obc_female_pass_second;
		int sc_male, sc_female, sc_female_pass, sc_male_pass, sc_male_pass_dis, sc_female_pass_dis, sc_male_pass_first, sc_female_pass_first, sc_male_pass_second, sc_female_pass_second;
		int st_male, st_female, st_female_pass, st_male_pass, st_male_pass_dis, st_female_pass_dis, st_male_pass_first, st_female_pass_first, st_male_pass_second, st_female_pass_second;
		
		general_male= general_female= general_male_pass= general_female_pass= general_male_pass_dis= general_female_pass_dis= general_female_pass_first= general_male_pass_first= general_female_pass_second= general_male_pass_second=0;
				obc_male= obc_female= obc_female_pass= obc_male_pass=obc_male_pass_dis=obc_female_pass_dis= obc_male_pass_first= obc_female_pass_first= obc_male_pass_second= obc_female_pass_second=0;
		sc_male= sc_female= sc_female_pass= sc_male_pass= sc_male_pass_dis= sc_female_pass_dis= sc_male_pass_first= sc_female_pass_first= sc_male_pass_second= sc_female_pass_second=0;
				st_male= st_female= st_female_pass= st_male_pass= st_male_pass_dis= st_female_pass_dis= st_male_pass_first= st_female_pass_first= st_male_pass_second= st_female_pass_second=0;

		
		// Create a File object
		File file = new File("D:\\PDF\\"+filename);

		// Get the file extension
		String fileExtension = getFileExtension(file);

		// Check if the file is a text file or an Excel file
		if (fileExtension.equalsIgnoreCase("txt")) 
		{	
			try
			{
				
				BufferedReader br = new BufferedReader(new FileReader("D:\\PDF\\"+filename));
				String rollNumberPattern = "\\b\\d{5}[a-zA-Z]\\d{5}\\b"; // Match 5 digits, followed by a letter, followed by 5 digits
				Pattern pattern = Pattern.compile(rollNumberPattern);
				String line="";
				while ((line = br.readLine()) != null) 
				{  
					Matcher matcher = pattern.matcher(line);
					if(line.contains("FIRST"))
					{
						sem = 1;
					}
					else if(line.contains("SECOND"))
					{
						sem = 2;
					}
					else if(line.contains("THIRD"))
					{
						sem = 3;
					}
					else if(line.contains("FOURTH"))
					{
						sem = 4;
					}
					else if(line.contains("FIFTH"))
					{
						sem = 5;
					}
					else if(line.contains("SIXTH"))
					{
						sem = 6;
					}
					if (matcher.find()) 
					{
						rollnumber = matcher.group();
						try
						{
							double sgpa=0.0;
							String result="";


							PDDocument document = PDDocument.load(new File("D:\\PDF\\"+pdfname));// here file1.pdf is the name of pdf file which we want to read....
							document.getClass();

							if (!document.isEncrypted())
							{
								String output="";
								PDFTextStripperByArea stripper = new PDFTextStripperByArea();
								stripper.setSortByPosition(true);
								PDFTextStripper Tstripper = new PDFTextStripper();
								String str = Tstripper.getText(document);
								Scanner scn = null;     
								scn = new Scanner(str);

								Boolean temp= false;
								StringBuilder sb = new StringBuilder();
								while (scn.hasNextLine()) 
								{  
									String lines="";
									lines = scn.nextLine();
									if(lines.contains(rollnumber))
									{
										temp = true;
										sb.append(lines.trim() + " ");
									}
									else if(temp == true)
									{
										sb.append(lines.trim() + " ");
										output = sb.toString().trim();
										if(lines.contains("FAIL") || lines.contains("PASS"))
										{
											//System.out.println(output);
											break;
										}
									}

								}
								Pattern patterns = Pattern.compile("\\b\\d+\\.\\d{2}\\b"); // Match digits followed by a decimal point and more digits
								Matcher matchers = patterns.matcher(output);

								if (matchers.find()) 	
								{
									String sgpaString = matchers.group();
									sgpa = Double.parseDouble(sgpaString);
								} 
								else 
								{
									//response.getWriter().println("SGPA not found");
								}

								if(output.contains("PASS"))
								{
									result="PASS";
								}
								else if(output.contains("FAIL"))
								{
									result="FAIL";
								}
								else 
								{
									//response.getWriter().println("Result not found");
								}	
							}
							document.close();

							//CHECK FOR ADMITTED STUDENTS-----
							if(line.contains("MALEMALE") && line.contains("GENERAL"))
							{
								general_male++;
							}
							else if(line.contains("FEMALE") && line.contains("GENERAL"))
							{
								general_female++;
							}

							//CHECK FOR PASSED-----
							if(result=="PASS" && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass++;
							}
							else if(result=="PASS" && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass++;
							}

							//CHECK FOR DISTINCTION-----
							if(result=="PASS" && sgpa>=7.50  && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass_dis++;
							}
							else if(result=="PASS" && sgpa>=7.50 && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass_dis++;
							}

							//CHECK FOR 1ST DIVISION-----
							if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass_first++;
							}
							else if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass_first++;
							}

							//CHECK FOR 2ND DIVISION-----
							if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass_second++;
							}
							else if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass_second++;
							}


							// CHECK FOR OBC STUDENTS-----
							if(line.contains("MALEMALE") && line.contains("OTHER_BACKWARD_CLASS"))
							{
								obc_male++;
							}
							else if(line.contains("FEMALE") && line.contains("OTHER_BACKWARD_CLASS"))
							{
								obc_female++;
							}

							if(result=="PASS" && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass++;
							}
							else if(result=="PASS" && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALE"))
							{
								obc_male_pass++;
							}

							//CHECK FOR DISTINCTION-----
							if(result=="PASS" && sgpa>=7.50  && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALEMALE"))
							{
								obc_male_pass_dis++;
							}
							else if(result=="PASS" && sgpa>=7.50 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass_dis++;
							}

							//CHECK FOR 1ST DIVISION-----
							if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALEMALE"))
							{
								obc_male_pass_first++;
							}
							else if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass_first++;
							}	

							//CHECK FOR 2ND DIVISION-----
							if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALEMALE"))
							{
								obc_male_pass_second++;
							}
							else if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass_second++;
							}


							// check for sc students
							if(line.contains("MALEMALE") && line.contains("SCHEDULE_CASTE"))
							{
								sc_male++;
							}
							else if(line.contains("FEMALE") && line.contains("SCHEDULE_CASTE"))
							{
								sc_female++;
							}
							if(result=="PASS" && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass++;
							}
							else if(result=="PASS" && line.contains("SCHEDULE_CASTE") && line.contains("MALE"))
							{
								sc_male_pass++;
							}

							//CHECK FOR DISTINCTION-----
							if(result=="PASS" && sgpa>=7.50  && line.contains("SCHEDULE_CASTE") && line.contains("MALEMALE"))
							{
								sc_male_pass_dis++;
							}
							else if(result=="PASS" && sgpa>=7.50 && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass_dis++;
							}

							//CHECK FOR 1ST DIVISION-----
							if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("SCHEDULE_CASTE") && line.contains("MALEMALE"))
							{
								sc_male_pass_first++;
							}
							else if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass_first++;
							}

							//CHECK FOR 2ND DIVISION-----
							if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("SCHEDULE_CASTE") && line.contains("MALEMALE"))
							{
								sc_male_pass_second++;
							}
							else if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass_second++;
							}

						}
						catch     (Exception e) 
						{
							//e.printStackTrace();
							response.getWriter().println(e);
						}	
					}
				}
				br.close();
				Class.forName("com.mysql.cj.jdbc.Driver");
				String connectionURL = "jdbc:mysql://localhost:3306/result";
				Connection con=DriverManager.getConnection(connectionURL, "root", "");
				Statement st=con.createStatement();
				int a=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('general','"+sem+"','"+general_male+"','"+general_female+"','"+general_male_pass+"','"+general_female_pass+"','"+general_male_pass_dis+"','"+general_female_pass_dis+"','"+general_male_pass_first+"','"+general_female_pass_first+"','"+general_male_pass_second+"','"+general_female_pass_second+"')");
				int b=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('obc','"+sem+"','"+obc_male+"','"+obc_female+"','"+obc_male_pass+"','"+obc_female_pass+"','"+obc_male_pass_dis+"','"+obc_female_pass_dis+"','"+obc_male_pass_first+"','"+obc_female_pass_first+"','"+obc_male_pass_second+"','"+obc_female_pass_second+"')");
				int c=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('sc','"+sem+"','"+sc_male+"','"+sc_female+"','"+sc_male_pass+"','"+sc_female_pass+"','"+sc_male_pass_dis+"','"+sc_female_pass_dis+"','"+sc_male_pass_first+"','"+sc_female_pass_first+"','"+sc_male_pass_second+"','"+sc_female_pass_second+"')");
				int d=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('st','"+sem+"','"+st_male+"','"+st_female+"','"+st_male_pass+"','"+st_female_pass+"','"+st_male_pass_dis+"','"+st_female_pass_dis+"','"+st_male_pass_first+"','"+st_female_pass_first+"','"+st_male_pass_second+"','"+st_female_pass_second+"')");
				//System.out.println("data inserted in database--------");
				if (a > 0 && b > 0 && c > 0 && d > 0) 
				{
					dispatcher = request.getRequestDispatcher("hometable.jsp");
				}
				else
				{
					response.getWriter().println("data not inserted");
				}
				dispatcher.forward(request, response);


			} 

			catch (Exception e) 
			{
				//e.printStackTrace();
				response.getWriter().println(e);
			}

		}
		else if (fileExtension.equalsIgnoreCase("xls") || fileExtension.equalsIgnoreCase("xlsx")) 
		{
			try
			{

				

				File myfile = new File("D:\\PDF\\"+filename); // Replace with the path to your Excel file
				FileInputStream fis = new FileInputStream(myfile);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheetAt(0); // Replace with the index of the sheet you want to read from

				String rollNumberPattern = "\\b\\d{5}[a-zA-Z]\\d{5}\\b"; // Match 5 digits, followed by a letter, followed by 5 digits
				Pattern pattern = Pattern.compile(rollNumberPattern);
				for (Row row : sheet) 
				{
					StringBuilder rowData = new StringBuilder();
					String line="";
					for (Cell cell : row) 
					{	
						rowData.append(cell.toString()).append("\t");
					}
					line=rowData.toString();

					Matcher matcher = pattern.matcher(line);
					if(line.contains("FIRST"))
					{
						sem = 1;
					}
					else if(line.contains("SECOND"))
					{
						sem = 2;
					}
					else if(line.contains("THIRD"))
					{
						sem = 3;
					}
					else if(line.contains("FOURTH"))
					{
						sem = 4;
					}
					else if(line.contains("FIFTH"))
					{
						sem = 5;
					}
					else if(line.contains("SIXTH"))
					{
						sem = 6;
					}


					if (matcher.find()) 
					{
						rollnumber = matcher.group();
						try
						{
							double sgpa=0.0;
							String result="";


							PDDocument document = PDDocument.load(new File("D:\\PDF\\"+pdfname));// here file1.pdf is the name of pdf file which we want to read....
							document.getClass();

							if (!document.isEncrypted())
							{
								String output="";
								PDFTextStripperByArea stripper = new PDFTextStripperByArea();
								stripper.setSortByPosition(true);
								PDFTextStripper Tstripper = new PDFTextStripper();
								String str = Tstripper.getText(document);
								Scanner scn = null;     
								scn = new Scanner(str);

								Boolean temp= false;
								StringBuilder sb = new StringBuilder();
								while (scn.hasNextLine()) 
								{  
									String lines="";
									lines = scn.nextLine();
									if(lines.contains(rollnumber))
									{
										temp = true;
										sb.append(lines.trim() + " ");
									}
									else if(temp == true)
									{
										sb.append(lines.trim() + " ");
										output = sb.toString().trim();
										if(lines.contains("FAIL") || lines.contains("PASS"))
										{
											//System.out.println(output);
											break;
										}
									}

								}
								Pattern patterns = Pattern.compile("\\b\\d+\\.\\d{2}\\b"); // Match digits followed by a decimal point and more digits
								Matcher matchers = patterns.matcher(output);

								if (matchers.find()) 	
								{
									String sgpaString = matchers.group();
									sgpa = Double.parseDouble(sgpaString);
								} 
								else 
								{
									//response.getWriter().println("SGPA not found");
								}

								if(output.contains("PASS"))
								{
									result="PASS";
								}
								else if(output.contains("FAIL"))
								{
									result="FAIL";
								}
								else 
								{
									//response.getWriter().println("Result not found");
								}	
							}
							document.close();

							//CHECK FOR ADMITTED STUDENTS-----
							if(line.contains("MALEMALE") && line.contains("GENERAL"))
							{
								general_male++;
							}
							else if(line.contains("FEMALE") && line.contains("GENERAL"))
							{
								general_female++;
							}

							//CHECK FOR PASSED-----
							if(result=="PASS" && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass++;
							}
							else if(result=="PASS" && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass++;
							}

							//CHECK FOR DISTINCTION-----
							if(result=="PASS" && sgpa>=7.50  && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass_dis++;
							}
							else if(result=="PASS" && sgpa>=7.50 && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass_dis++;
							}

							//CHECK FOR 1ST DIVISION-----
							if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass_first++;
							}
							else if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass_first++;
							}

							//CHECK FOR 2ND DIVISION-----
							if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("GENERAL") && line.contains("MALEMALE"))
							{
								general_male_pass_second++;
							}
							else if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("GENERAL") && line.contains("FEMALE"))
							{
								general_female_pass_second++;
							}


							// CHECK FOR OBC STUDENTS-----
							if(line.contains("MALEMALE") && line.contains("OTHER_BACKWARD_CLASS"))
							{
								obc_male++;
							}
							else if(line.contains("FEMALE") && line.contains("OTHER_BACKWARD_CLASS"))
							{
								obc_female++;
							}

							if(result=="PASS" && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass++;
							}
							else if(result=="PASS" && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALE"))
							{
								obc_male_pass++;
							}

							//CHECK FOR DISTINCTION-----
							if(result=="PASS" && sgpa>=7.50  && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALEMALE"))
							{
								obc_male_pass_dis++;
							}
							else if(result=="PASS" && sgpa>=7.50 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass_dis++;
							}

							//CHECK FOR 1ST DIVISION-----
							if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALEMALE"))
							{
								obc_male_pass_first++;
							}
							else if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass_first++;
							}	

							//CHECK FOR 2ND DIVISION-----
							if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("MALEMALE"))
							{
								obc_male_pass_second++;
							}
							else if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("OTHER_BACKWARD_CLASS") && line.contains("FEMALE"))
							{
								obc_female_pass_second++;
							}


							// check for sc students
							if(line.contains("MALEMALE") && line.contains("SCHEDULE_CASTE"))
							{
								sc_male++;
							}
							else if(line.contains("FEMALE") && line.contains("SCHEDULE_CASTE"))
							{
								sc_female++;
							}
							if(result=="PASS" && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass++;
							}
							else if(result=="PASS" && line.contains("SCHEDULE_CASTE") && line.contains("MALE"))
							{
								sc_male_pass++;
							}

							//CHECK FOR DISTINCTION-----
							if(result=="PASS" && sgpa>=7.50  && line.contains("SCHEDULE_CASTE") && line.contains("MALEMALE"))
							{
								sc_male_pass_dis++;
							}
							else if(result=="PASS" && sgpa>=7.50 && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass_dis++;
							}

							//CHECK FOR 1ST DIVISION-----
							if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("SCHEDULE_CASTE") && line.contains("MALEMALE"))
							{
								sc_male_pass_first++;
							}
							else if(result=="PASS" && sgpa>=6.50 && sgpa<=7.49 && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass_first++;
							}

							//CHECK FOR 2ND DIVISION-----
							if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("SCHEDULE_CASTE") && line.contains("MALEMALE"))
							{
								sc_male_pass_second++;
							}
							else if(result=="PASS" && sgpa>=5.0 && sgpa<=6.49 && line.contains("SCHEDULE_CASTE") && line.contains("FEMALE"))
							{
								sc_female_pass_second++;
							}

						}
						catch (Exception e) 
						{
							//e.printStackTrace();
							response.getWriter().println(e);
						}	
					}
				}
				fis.close();
			

				Class.forName("com.mysql.cj.jdbc.Driver");
				String connectionURL = "jdbc:mysql://localhost:3306/result";
				Connection con=DriverManager.getConnection(connectionURL, "root", "");
				Statement st=con.createStatement();
				int a=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('general','"+sem+"','"+general_male+"','"+general_female+"','"+general_male_pass+"','"+general_female_pass+"','"+general_male_pass_dis+"','"+general_female_pass_dis+"','"+general_male_pass_first+"','"+general_female_pass_first+"','"+general_male_pass_second+"','"+general_female_pass_second+"')");
				int b=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('obc','"+sem+"','"+obc_male+"','"+obc_female+"','"+obc_male_pass+"','"+obc_female_pass+"','"+obc_male_pass_dis+"','"+obc_female_pass_dis+"','"+obc_male_pass_first+"','"+obc_female_pass_first+"','"+obc_male_pass_second+"','"+obc_female_pass_second+"')");
				int c=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('sc','"+sem+"','"+sc_male+"','"+sc_female+"','"+sc_male_pass+"','"+sc_female_pass+"','"+sc_male_pass_dis+"','"+sc_female_pass_dis+"','"+sc_male_pass_first+"','"+sc_female_pass_first+"','"+sc_male_pass_second+"','"+sc_female_pass_second+"')");
				int d=st.executeUpdate("insert into " + user_name + "(category, semester, admitted_boys, admitted_girls, passed_boys, passed_girls, distinction_boys, distinction_girls, first_boys, first_girls, second_boys, second_girls) values('st','"+sem+"','"+st_male+"','"+st_female+"','"+st_male_pass+"','"+st_female_pass+"','"+st_male_pass_dis+"','"+st_female_pass_dis+"','"+st_male_pass_first+"','"+st_female_pass_first+"','"+st_male_pass_second+"','"+st_female_pass_second+"')");
				//System.out.println("data inserted in database--------");
				if (a > 0 && b > 0 && c > 0 && d > 0) 
				{
					dispatcher = request.getRequestDispatcher("hometable.jsp");
				}
				else
				{
					response.getWriter().println("data not inserted");
				}
				dispatcher.forward(request, response);
			}
			catch (Exception e) 
			{
				//e.printStackTrace();
				response.getWriter().println(e);
			}

		} 



	}

	private static String getFileExtension(File file) 
	{
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex == -1 || dotIndex == 0 || dotIndex == fileName.length() - 1) 
		{
			return ""; // File doesn't have an extension or it starts/ends with a dot
		}
		return fileName.substring(dotIndex + 1).toLowerCase();
	}

}
