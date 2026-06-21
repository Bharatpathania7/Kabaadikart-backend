////package KabaadiKart.kabaadikart_backend.service;
////
////import KabaadiKart.kabaadikart_backend.model.Home;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.mail.SimpleMailMessage;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.stereotype.Service;
////
////@Service
////public class EmailService {
////
////    @Autowired
////    private JavaMailSender mailSender;
////
////    private final String TEAM_EMAIL = "scrapsectorbusiness@gmail.com";
////
////    public void sendBookingToTeam(Home booking) {
////        String subject = "New Booking Pending Confirmation - " + booking.getBookingId();
////        String body = "New booking received:\n\n" +
////                "Booking ID: " + booking.getBookingId() + "\n" +
////                "Name: " + booking.getName() + "\n" +
////                "Mobile: " + booking.getMobile() + "\n" +
////                "Address: " + booking.getAddress() + ", " + booking.getCity() + " - " + booking.getPincode() + "\n" +
////                "Landmark: " + booking.getLandmark() + "\n" +
////                "Scrap Type: " + booking.getScrapType() + "\n" +
////                "Quantity: " + booking.getQuantity() + "\n" +
////                "Pickup Date: " + booking.getPickupDate() + " (" + booking.getPickupSlot() + ")\n" +
////                "Status: " + booking.getStatus() + "\n" +
////                "Created At: " + booking.getCreatedAt();
////
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setTo(TEAM_EMAIL);
////        message.setSubject(subject);
////        message.setText(body);
////
////        mailSender.send(message);
////    }
////
////}
//package KabaadiKart.kabaadikart_backend.service;
//
//import KabaadiKart.kabaadikart_backend.model.Home;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    private final String TEAM_EMAIL = "scrapsectorbusiness@gmail.com";
//    private final String FROM_NAME = "ScrapSector";
//
//    /** Plain text email (team notifications) */
//    public void sendBookingToTeam(Home booking) {
//        String subject = "New Booking Pending Confirmation - " + booking.getBookingId();
//        String body = "New booking received:\n\n" +
//                "Booking ID: " + booking.getBookingId() + "\n" +
//                "Name: " + booking.getName() + "\n" +
//                "Mobile: " + booking.getMobile() + "\n" +
//                "Address: " + booking.getAddress() + ", " + booking.getCity() + " - " + booking.getPincode() + "\n" +
//                "Landmark: " + booking.getLandmark() + "\n" +
//                "Scrap Type: " + booking.getScrapType() + "\n" +
//                "Quantity: " + booking.getQuantity() + "\n" +
//                "Pickup Date: " + booking.getPickupDate() + " (" + booking.getPickupSlot() + ")\n" +
//                "Status: " + booking.getStatus() + "\n" +
//                "Created At: " + booking.getCreatedAt();
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(TEAM_EMAIL);
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//    }
//
//    /** Send beautiful confirmation email to customer */
//    public void sendBookingConfirmation(Home booking, String toEmail) {
//        try {
//            String subject = "✅ Booking Confirmed - " + booking.getBookingId();
//            String htmlBody = buildBookingConfirmationHtml(booking);
//
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                            StandardCharsets.UTF_8.name());
//
//            helper.setFrom(TEAM_EMAIL, FROM_NAME);
//            helper.setTo(toEmail);
//            helper.setSubject(subject);
//            helper.setText(htmlBody, true);
//
//            mailSender.send(helper.getMimeMessage());
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to send booking confirmation email: " + e.getMessage(), e);
//        }
//    }
//
//    /** Build nice branded HTML email */
//    private String buildBookingConfirmationHtml(Home booking) {
//        return """
//            <div style="font-family:Inter,Arial,sans-serif;background:#f6f8fb;padding:24px;">
//              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0"
//                     style="max-width:600px;margin:auto;background:#ffffff;border-radius:16px;
//                            box-shadow:0 6px 18px rgba(20,28,45,.06);overflow:hidden;">
//
//                <!-- Header -->
//                <tr>
//                  <td style="background:#16a34a;padding:20px 24px;color:#fff;text-align:center;">
//                    <img src="https://my-introombucket.s3.ap-south-1.amazonaws.com/uploads/logo+merch.jpg"
//                         alt="ScrapSector Logo" style="max-height:60px; margin-bottom:8px;"/>
//                    <div style="font-size:22px;font-weight:700;">Booking Confirmed ✅</div>
//                  </td>
//                </tr>
//
//                <!-- Body -->
//                <tr>
//                  <td style="padding:24px;color:#1f2937;">
//                    <div style="font-size:16px;">Hello <strong>%s</strong>,</div>
//                    <div style="margin-top:12px;font-size:14px;line-height:1.6;">
//                      Great news! 🎉 Your booking has been <b>confirmed</b>.
//                      <br/>Here are your booking details:
//                    </div>
//
//                    <table role="presentation" width="100%%" cellpadding="0" cellspacing="0"
//                           style="margin-top:16px;font-size:14px;color:#374151;">
//                      <tr>
//                        <td style="padding:6px 0;font-weight:600;width:140px;">Booking ID:</td>
//                        <td>%s</td>
//                      </tr>
//                      <tr>
//                        <td style="padding:6px 0;font-weight:600;">Name:</td>
//                        <td>%s</td>
//                      </tr>
//                      <tr>
//                        <td style="padding:6px 0;font-weight:600;">Mobile:</td>
//                        <td>%s</td>
//                      </tr>
//                      <tr>
//                        <td style="padding:6px 0;font-weight:600;">Status:</td>
//                        <td><span style="color:#16a34a;font-weight:700;">Confirmed</span></td>
//                      </tr>
//                    </table>
//
//                    <div style="margin-top:20px;font-size:14px;">
//                      ♻️ Thank you for choosing <strong>ScrapSector</strong>.
//                      We’ll keep you updated on the next steps.
//                    </div>
//                  </td>
//                </tr>
//
//                <!-- Footer -->
//                <tr>
//                  <td style="padding:18px 24px 24px;color:#6b7280;font-size:12px;border-top:1px solid #f1f5f9;text-align:center;">
//                    If you have any questions, reply to this email or contact support.
//                    <br/>— Team ScrapSector
//                  </td>
//                </tr>
//              </table>
//            </div>
//            """.formatted(
//                escape(booking.getName()),
//                escape(booking.getBookingId()),
//                escape(booking.getName()),
//                escape(booking.getMobile())
//        );
//    }
//
//    /** Utility: escape special chars for HTML safety */
//    private String escape(String s) {
//        if (s == null) return "";
//        return s.replace("&", "&amp;")
//                .replace("<", "&lt;")
//                .replace(">", "&gt;");
//    }
//}
package KabaadiKart.kabaadikart_backend.service;

import KabaadiKart.kabaadikart_backend.model.Home;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String TEAM_EMAIL = "scrapsectorbusiness@gmail.com";
    private final String FROM_NAME = "ScrapSector";

    /** Plain text email (team notifications) */
    public void sendBookingToTeam(Home booking) {
        String subject = "New Booking Pending Confirmation - " + booking.getBookingId();
        String body = "New booking received:\n\n" +
                "Booking ID: " + booking.getBookingId() + "\n" +
                "Name: " + booking.getName() + "\n" +
                "Mobile: " + booking.getMobile() + "\n" +
                "Address: " + booking.getAddress() + ", " + booking.getCity() + " - " + booking.getPincode() + "\n" +
                "Landmark: " + booking.getLandmark() + "\n" +
                "Scrap Type: " + booking.getScrapType() + "\n" +
                "Quantity: " + booking.getQuantity() + "\n" +
                "Pickup Date: " + booking.getPickupDate() + " (" + booking.getPickupSlot() + ")\n" +
                "Status: " + booking.getStatus() + "\n" +
                "Created At: " + booking.getCreatedAt();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(TEAM_EMAIL);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    /** Send confirmation email to customer */
    public void sendBookingConfirmation(Home booking, String toEmail) {
        sendHtmlMail(toEmail,
                "✅ Booking Confirmed - " + booking.getBookingId(),
                buildBookingConfirmationHtml(booking));
    }

    /** Send pickup started email to customer */
    public void sendPickupStarted(Home booking, String toEmail) {
        sendHtmlMail(toEmail,
                "🚚 Pickup Started - " + booking.getBookingId(),
                buildPickupHtml(booking));
    }

    /** Helper: Send HTML mail */
    private void sendHtmlMail(String toEmail, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                            StandardCharsets.UTF_8.name());

            try {
                helper.setFrom(TEAM_EMAIL, FROM_NAME);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    /** HTML for confirmation */
    private String buildBookingConfirmationHtml(Home booking) {
        return """
            <div style="font-family:Inter,Arial,sans-serif;background:#f6f8fb;padding:24px;">
              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" 
                     style="max-width:600px;margin:auto;background:#ffffff;border-radius:16px;
                            box-shadow:0 6px 18px rgba(20,28,45,.06);overflow:hidden;">
                
                <tr>
                  <td style="background:#16a34a;padding:20px 24px;color:#fff;text-align:center;">
                    <img src="https://my-introombucket.s3.ap-south-1.amazonaws.com/uploads/logo+merch.jpg" 
                         alt="ScrapSector Logo" style="max-height:60px; margin-bottom:8px;"/>
                    <div style="font-size:22px;font-weight:700;">Booking Confirmed ✅</div>
                  </td>
                </tr>
                
                <tr>
                  <td style="padding:24px;color:#1f2937;">
                    <div style="font-size:16px;">Hello <strong>%s</strong>,</div>
                    <div style="margin-top:12px;font-size:14px;line-height:1.6;">
                      Great news! 🎉 Your booking has been <b>confirmed</b>.
                      <br/>Here are your booking details:
                    </div>
                    
                    <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" 
                           style="margin-top:16px;font-size:14px;color:#374151;">
                      <tr><td style="padding:6px 0;font-weight:600;width:140px;">Booking ID:</td><td>%s</td></tr>
                      <tr><td style="padding:6px 0;font-weight:600;">Name:</td><td>%s</td></tr>
                      <tr><td style="padding:6px 0;font-weight:600;">Mobile:</td><td>%s</td></tr>
                      <tr><td style="padding:6px 0;font-weight:600;">Status:</td>
                          <td><span style="color:#16a34a;font-weight:700;">Confirmed</span></td></tr>
                    </table>
                    
                    <div style="margin-top:20px;font-size:14px;">
                      ♻️ Thank you for choosing <strong>ScrapSector</strong>.  
                      We’ll keep you updated on the next steps.
                    </div>
                  </td>
                </tr>
                
                <tr>
                  <td style="padding:18px 24px 24px;color:#6b7280;font-size:12px;border-top:1px solid #f1f5f9;text-align:center;">
                    If you have any questions, reply to this email or contact support.  
                    <br/>— Team ScrapSector
                  </td>
                </tr>
              </table>
            </div>
            """.formatted(
                escape(booking.getName()),
                escape(booking.getBookingId()),
                escape(booking.getName()),
                escape(booking.getMobile())
        );
    }

    /** HTML for pickup started */
    private String buildPickupHtml(Home booking) {
        return """
            <div style="font-family:Inter,Arial,sans-serif;background:#f6f8fb;padding:24px;">
              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" 
                     style="max-width:600px;margin:auto;background:#ffffff;border-radius:16px;
                            box-shadow:0 6px 18px rgba(20,28,45,.06);overflow:hidden;">
                
                <tr>
                  <td style="background:#f97316;padding:20px 24px;color:#fff;text-align:center;">
                    <img src="https://my-introombucket.s3.ap-south-1.amazonaws.com/uploads/logo+merch.jpg" 
                         alt="ScrapSector Logo" style="max-height:60px; margin-bottom:8px;"/>
                    <div style="font-size:22px;font-weight:700;">Pickup Started 🚚</div>
                  </td>
                </tr>
                
                <tr>
                  <td style="padding:24px;color:#1f2937;">
                    <div style="font-size:16px;">Hello <strong>%s</strong>,</div>
                    <div style="margin-top:12px;font-size:14px;line-height:1.6;">
                      Good news! 🎉 Your pickup has <b>started</b>.
                      <br/>Here are your booking details:
                    </div>
                    
                    <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" 
                           style="margin-top:16px;font-size:14px;color:#374151;">
                      <tr><td style="padding:6px 0;font-weight:600;width:140px;">Booking ID:</td><td>%s</td></tr>
                      <tr><td style="padding:6px 0;font-weight:600;">Name:</td><td>%s</td></tr>
                      <tr><td style="padding:6px 0;font-weight:600;">Mobile:</td><td>%s</td></tr>
                      <tr><td style="padding:6px 0;font-weight:600;">Status:</td>
                          <td><span style="color:#f97316;font-weight:700;">Start Pickup</span></td></tr>
                    </table>
                    
                    <div style="margin-top:20px;font-size:14px;">
                      ♻️ Thank you for choosing <strong>ScrapSector</strong>.  
                      Our team is on the way!
                    </div>
                  </td>
                </tr>
                
                <tr>
                  <td style="padding:18px 24px 24px;color:#6b7280;font-size:12px;border-top:1px solid #f1f5f9;text-align:center;">
                    If you have any questions, reply to this email or contact support.  
                    <br/>— Team ScrapSector
                  </td>
                </tr>
              </table>
            </div>
            """.formatted(
                escape(booking.getName()),
                escape(booking.getBookingId()),
                escape(booking.getName()),
                escape(booking.getMobile())
        );
    }
    /** Send booking completed email to customer */
    public void sendBookingCompleted(Home booking, String toEmail) {
        sendHtmlMail(toEmail,
                "✅ Booking Completed - " + booking.getBookingId(),
                buildCompletedHtml(booking));
    }

    /** HTML for booking completed */
    private String buildCompletedHtml(Home booking) {
        return """
        <div style="font-family:Inter,Arial,sans-serif;background:#f6f8fb;padding:24px;">
          <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" 
                 style="max-width:600px;margin:auto;background:#ffffff;border-radius:16px;
                        box-shadow:0 6px 18px rgba(20,28,45,.06);overflow:hidden;">
            
            <tr>
              <td style="background:#2563eb;padding:20px 24px;color:#fff;text-align:center;">
                <img src="https://my-introombucket.s3.ap-south-1.amazonaws.com/uploads/logo+merch.jpg" 
                     alt="ScrapSector Logo" style="max-height:60px; margin-bottom:8px;"/>
                <div style="font-size:22px;font-weight:700;">Booking Completed ✅</div>
              </td>
            </tr>
            
            <tr>
              <td style="padding:24px;color:#1f2937;">
                <div style="font-size:16px;">Hello <strong>%s</strong>,</div>
                <div style="margin-top:12px;font-size:14px;line-height:1.6;">
                  Your booking has been <b>completed</b> successfully. Thank you for choosing <strong>ScrapSector</strong>! ♻️
                  <br/>Here are your booking details:
                </div>
                
                <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" 
                       style="margin-top:16px;font-size:14px;color:#374151;">
                  <tr><td style="padding:6px 0;font-weight:600;width:140px;">Booking ID:</td><td>%s</td></tr>
                  <tr><td style="padding:6px 0;font-weight:600;">Name:</td><td>%s</td></tr>
                  <tr><td style="padding:6px 0;font-weight:600;">Mobile:</td><td>%s</td></tr>
                  <tr><td style="padding:6px 0;font-weight:600;">Status:</td>
                      <td><span style="color:#2563eb;font-weight:700;">Completed</span></td></tr>
                </table>
                
                <div style="margin-top:20px;font-size:14px;">
                  We hope to serve you again soon!
                </div>
              </td>
            </tr>
            
            <tr>
              <td style="padding:18px 24px 24px;color:#6b7280;font-size:12px;border-top:1px solid #f1f5f9;text-align:center;">
                If you have any questions, reply to this email or contact support.  
                <br/>— Team ScrapSector
              </td>
            </tr>
          </table>
        </div>
        """.formatted(
                escape(booking.getName()),
                escape(booking.getBookingId()),
                escape(booking.getName()),
                escape(booking.getMobile())
        );
    }


    /** Utility: escape special chars for HTML safety */
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
