package com.nsa.mhasite.email;
import com.nsa.mhasite.domain.Business;
import com.nsa.mhasite.domain.Reward;
import com.nsa.mhasite.domain.User;
import com.nsa.mhasite.domain.VolunteerUser;
import com.nsa.mhasite.domain.Achievement;
import com.nsa.mhasite.domain.GiftForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailSessionBean {

    @Autowired
    public JavaMailSender emailSender;

    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Async
    public void welcomeEmail(String address, String password, String name) {
        String message = "Welcome to the MHA Volunteering Service " + name + "! \n\n";
        message += "Your temporary password is: ";
        message += password;
        message += "\n\nPlease visit http://localhost:8081/profile/details to change it";
        sendEmail(address, "Welcome to the MHA volunteering service!", message);
    }

    @Async
    public void redemptionEmailUser(User user, Reward reward, VolunteerUser volunteerUser, String code) {
        String message = "Hi " + volunteerUser.getFirstName() + "! \n\n";
        message += "Congratulations, you have redeemed: " + reward.getName() + "\n\n";
        message += "Your redemption code is : " + code + "\n\n";
        message += "Please show this code to the business to receive your reward\n\n";
        message += "Kind regards\n";
        message += "Monmouthshire Housing Association";
        sendEmail(user.getUsername(), "Your reward is ready!", message);
    }

    @Async
    public void redemptionEmailBusiness(Business business, Reward reward, VolunteerUser volunteerUser, String code) {
        String message = business.getBname() + "\n\n";
        message += volunteerUser.getFirstName() + " " + volunteerUser.getLastName() + " has redeemed your reward: " + reward.getName() + "\n\n";
        message += "Their unique redemption code is: " + code + "\n\n";
        message += "Kind regards\n";
        message += "Monmouthshire Housing Association";
        sendEmail(business.getBusinessEmail(), "MHA reward redeemed by " + volunteerUser.getFirstName() + " " + volunteerUser.getLastName(), message);
    }

    @Async
    public void achievementEmail(User user, VolunteerUser volunteerUser, Achievement achievement) {
        String message = "Hi " + volunteerUser.getFirstName() + "! \n\n";
        message += "Congratulations, you have earned the Achievement: " + achievement.getName() + "\n";
        message += achievement.getDescription() + "\n\n";
        message += "Keep up the good work!\n\n";
        message += "Kind regards\n";
        message += "Monmouthshire Housing Association";
        sendEmail(user.getUsername(), "New Achievement Earned!", message);
    }

    @Async
    public void giftEmail(GiftForm giftForm, VolunteerUser volunteerUser, Reward reward, String code) {
        String message = "Hi " + giftForm.getName() + "! \n\n";
        message += "You have received a gift from " + volunteerUser.getFirstName() + "\n\n";
        message += giftForm.getMessage() + "\n\n";
        message += "They have sent you the reward :" + reward.getName() + "\n\n";
        message += "Your redemption code is : " + code + "\n\n";
        message += "Please show this code to the business to receive your reward\n\n";
        message += "Kind regards\n";
        message += "Monmouthshire Housing Association";
        sendEmail(giftForm.getEmail1(), "Gift received from " + volunteerUser.getFirstName() + "!", message);
    }

    @Async
    public void giftEmailBusiness(GiftForm giftForm, Business business, Reward reward, VolunteerUser volunteerUser, String code) {
        String message = business.getBname() + "\n\n";
        message += giftForm.getName() + " has redeemed your reward: " + reward.getName() + "\n\n";
        message += "Their unique redemption code is: " + code + "\n\n";
        message += "Kind regards\n";
        message += "Monmouthshire Housing Association";
        sendEmail(business.getBusinessEmail(), "MHA reward redeemed by " + giftForm.getName(), message);
    }

    @Async
    public void giftEmailSender(GiftForm giftForm, User user, VolunteerUser volunteerUser, Reward reward) {
        String message = "Hi " + volunteerUser.getFirstName() + "! \n\n";
        message += "Congratulations, you have successfully gifted: " + reward.getName() + "\n\n";
        message += "To : " + giftForm.getName() + "\n\n";
        message += "They have received an email with their unique code\n\n";
        message += "Kind regards\n";
        message += "Monmouthshire Housing Association";
        sendEmail(user.getUsername(), "Your gift has been sent!", message);
    }
}
