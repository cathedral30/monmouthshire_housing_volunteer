# Team 9 MHA

## Setup

* To generate a local copy of the Database Schemas, run [creation.sql](sql/creation.sql) in the sql folder.
* Then run [account_creation.sql](sql/account_creation.sql) in the sql folder to create the MHA database account.
* The application properties file assumes the database is running locally on port 3306, the file will need to altered if this is not the case.
* Run gradle build.

## User Tests
### Registration
* Navigate to the website [home](http://localhost:8081/) and click on ["create new account"](http://localhost:8081/application).
* Fill in your details, you will be informed if you have missed any required boxes, a functional email address is important for testing automated emails.
* If successful, a green box will appear informing you that the application was successful with a link back to the home, click this.
* Login using the email: "admin@mha.com" and password: "password".
* Click the link to access the admin homepage and then click on the "Applicants for volunteer" link on the left hand side.
* You will see your application listed, with Accept and Decline buttons and all relevant information. Click accept.
### Administrator Tests
* Click on the "Search for Volunteers" link and then click the "Search for Volunteer" button without entering anything into the search box.
* You will see your newly registered account listed and also another preregistered account with the name "Ben Morris".
* Click on the "Search for Volunteers" link again and then enter either your First or Last name into the search box before clicking the search button.
* You will now only see your account in the search results. Click the details button, this will take you to a page showing your information and allows an admin to change the email for an account or other information at a user's request.
* Go back to the search results and click the "pay credits" button. This takes you to a page for giving time credits to the volunteer, enter 10 into the "pay amount" box and a reason into the "reason for credits box", then press submit.
* Click the "Add new business" link on the left toolbar. This page allows admins to register a new business. Enter a business name into the "Business name" box and then an email into the "Business email" box. If the email given is valid, you will be able to see the emails a business receives.
* You will be informed that a business has been created successfully. Click the "Change Business Info" link on the left toolbar.
* This will direct you to a page where you can change a business' email or name. A preregistered business called 'Cracking Cupcake Company' will be in the dropdown menus along with your new business.
* Change the name of the "Cracking Cake Company" to a new cake business name of your choice by selecting it in the dropdown under "Change Business Name" and then enter the new name in the 2 other boxes beneath. Then click the Submit button.
* Click on the "Add new Reward" link on the left toolbar. On this page, new rewards can be added to the businesses.
* Enter a reward name, description and reward cost. Set the cost as 2 to make sure there are enough time credits for later tests. Select your new business on the dropdown and click submit.
* Click the "Search for Rewards" link in the left toolbar and then the "Search for Reward" button without entering a search term.
* All currently registered rewards will be listed. 2 rewards were premade; "Free Cupcake" and "Free Mug of Shale Oil", the business name for these will have changed to whatever you changed the "Cracking Cake Company" name to as these were registered to that business.
* Click the red "deactivate" button on the "Free Mug of Shale Oil" reward and then click the search button again. You will see that the reward is no longer active. This means that volunteers will not see it listed in the available rewards.
* Click the "Rewards Statistics" link on the left toolbar, this will show a list of all rewards listed in order of redemptions, change the dropdown to "Alphabetical" and all rewards will be listed by order of Reward name.
* Click on the "Hello, Admin!" button at the top right of the portal, then click "Sign Out".
### Volunteer Tests
* Check your emails, you will have received one with the subject "Welcome to the MHA volunteering service!", it will have a temporary password included. Click the link in the email and login with your email and the provided temporary password.
* You will now be at the "Change Details" page which can be accessed otherwise by the dropdown in the top right with your name on by selecting the ["Manage Account"](http://localhost:8081/profile/details) link.
* Type your temporary password into the "Current Password" box and then enter a new password into the next 2 boxes and click the "Change Password" button.
* Click the "Your Time Bank" link on the left toolbar, here you will see the 10 credits which were allocated earlier and also 2 achievements which you have earned by receiving those credits. You will also have an email about each achievement.
* The achievements earned will be "Started Volunteering" and "Experienced Volunteer". The date on which they were earned will also be displayed along with a short message publicising a real partnered business.
* Click the "View Rewards" link in the left toolbar. This will display a list of all active rewards, currently including the "Free Cupcake" reward and your created one.
* Click the name of your offer, this will open a page where you can either redeem the reward or gift it.
* Click the "Redeem" button, it will inform you that it has been "Successfully redeemed". Now click the "Gift" button.
* The gifting page will have opened, enter a name, email address twice and a gift message, the click the "Gift" Button. If the email address is a real one, you will receive an email notifying you of the gift.
* You will be informed that the gift has been sent.
* Check your emails again, you will have one which notifies you that your gift was sent and also one which informs you that your reward is ready (this was sent when you clicked the redeem button). The email will include a redemption code which the business also receives a copy of.
* Return to the website and click the "View Credit History" link in the left toolbar. It will show 2 redemptions of your reward including the redemption code. The first is from when you redeemed it for yourself and the second is from the gifting. The date and time will be included.
* Finally Click the "Your Time Bank" link in the left toolbar and you will see that your current balance has decreased by the required amount. The Total Amount will not have decreased as it is a record of all credits earned so does not decrease.
* Tests complete.

