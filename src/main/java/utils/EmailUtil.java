package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailUtil 
{
	
	private static final Logger log = LogManager.getLogger(EmailUtil.class);
    public static void sendExecutionReport( int passed, int failed,int skipped, String executionDuration, String executionStart,String executionEnd) 
    {
    

       final String from = PropReader.getExecutorEmail().trim();
       
       final String password = PropReader.getExecutorAppPassword().trim();

         
        boolean hasFailures = failed > 0;

        String recipients = PropReader.getQARecipients();
        
        if (hasFailures)
        {
            recipients += "," + PropReader.getManagerRecipients();
        }

        String projectDir = System.getProperty("user.dir");

        String extentReport = projectDir + "/target/ExtentReport.html";
       
        String cucumberReport = projectDir + "/target/cucumber-report/cucumber.html";

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(from, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipients)
            );

            message.setSubject("Automation Report | " +
                    PropReader.getEnv().toUpperCase() +
                    (hasFailures ? " ❌ FAILED" : " ✅ PASSED"));

            String htmlBody =
            	    "<html><body style='font-family:Arial'>" +
            	    "<p>Hi,</p>" +
            	    "<p>Please find the latest automation reports attached:</p>" +
            	    "<ul>" +
            	    "<li>Extent Report</li>" +
            	    "<li>Cucumber Report</li>" +
            	    "</ul>" +
            	    "<h2>Augmount Automation Execution Summary</h2>" +
            	    "<table border='1' cellpadding='8'>" +
            	    "<tr><td>Environment</td><td>" + PropReader.getEnv() + "</td></tr>" +
            	    "<tr><td>Executed By</td><td>" + PropReader.getExecutorName("executor.name") + "</td></tr>" +
            	    "<tr><td>Start Time</td><td>" + executionStart + "</td></tr>" +
            	    "<tr><td>End Time</td><td>" + executionEnd + "</td></tr>" +
            	    "<tr><td>Total Duration</td><td>" + executionDuration + "</td></tr>" +
            	    "</table><br>" +
            	    "<table border='1' cellpadding='8'>" +
            	    "<tr><th>Passed</th><th>Failed</th><th>Skipped</th></tr>" +
            	    "<tr align='center'>" +
            	    "<td style='color:green'>" + passed + "</td>" +
            	    "<td style='color:red'>" + failed + "</td>" +
            	    "<td style='color:orange'>" + skipped + "</td>" +
            	    "</tr></table>" +
            	    "<br><p>Thanks & Regards,</b></p>"+
            	    "<br><b>QA Automation Team</b></p>" +
            	    "</body></html>";


            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(htmlBody, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            attachIfExists(multipart, extentReport);
            attachIfExists(multipart, cucumberReport);

            message.setContent(multipart);
            Transport.send(message);

           log.info("Execution report email sent successfully");

        } 
        catch (Exception e) 
        {
           log.error("Failed to send execution email", e);
        }
    }

    private static void attachIfExists(Multipart multipart, String filePath)throws Exception 
    {

        File file = new File(filePath);
        
        if (file.exists()) 
        {
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(file);
            multipart.addBodyPart(attachment);
        }
    }
}
