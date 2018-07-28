package com.globoforce.mentoring.testautomation.uitesting.services;

import com.globoforce.mentoring.testautomation.uitesting.businessobject.User;
import com.globoforce.mentoring.testautomation.uitesting.pages.LandingPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.nomination.Nomination;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class NominationService {

    private WebDriver driver;
    private String nominateFrom;
    private String recipient;
    private String awardProgram;
    private String awardReason;
    private String awardAdvisorMandatoryOrOptional;
    private String awardLevel;
    private String awardTitle;
    private String awardMessage;
    private String messageToApprover;
    private String confirmationMessage;

    private Nomination nomination;
    private LandingPage landingPage;

    public static class NominationBuilder{
        private WebDriver driver;
        private String nominateFrom;
        private String recipient;
        private String awardProgram;
        private String awardReason;
        private String awardAdvisorMandatoryOrOptional;
        private String awardLevel;
        private String awardTitle;
        private String messageToApprover;
        private String awardMessage;
        private String confirmationMessage;


        /*
        @params nominateFrom, possible values: Menu, My Team
         */
        public NominationBuilder setPlaceToStartNomination(String nominateFrom) {
            this.nominateFrom = nominateFrom;
            return this;
        }

        public NominationBuilder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public NominationBuilder setAwardProgram(String awardProgram) {
            this.awardProgram = awardProgram;
            return this;
        }

        public NominationBuilder setAwardReason(String awardReason) {
            this.awardReason = awardReason;
            return this;
        }

        /*
        * possible values: at the moment only Optional
        */
        public NominationBuilder setAwardAdvisorMandatoryOrOptional(String awardAdvisorMandatoryOrOptional) {
            this.awardAdvisorMandatoryOrOptional = awardAdvisorMandatoryOrOptional;
            return this;
        }

        public NominationBuilder setAwardLevel(String awardLevel) {
            this.awardLevel = awardLevel;
            return this;
        }

        public NominationBuilder setAwardTitle(String awardTitle) {
            this.awardTitle = awardTitle;
            return this;
        }

        public NominationBuilder setAwardMessage(String awardMessage) {
            this.awardMessage = awardMessage;
            return this;
        }

        public NominationBuilder setMessageToApprover(String messageToApprover) {
            this.messageToApprover = messageToApprover;
            return this;
        }

        public NominationBuilder setConfirmationMessage(String confirmationMessage) {
            this.confirmationMessage = confirmationMessage;
            return this;
        }

        public NominationBuilder(WebDriver driver) {
            this.driver = driver;
        }

        public Nomination placeNomination() throws InterruptedException {
            NominationService nomination = new NominationService();
            nomination.nominateFrom = this.nominateFrom;
            nomination.recipient = this.recipient;
            nomination.awardProgram = this.awardProgram;
            nomination.awardReason = this.awardReason;
            nomination.awardAdvisorMandatoryOrOptional = this.awardAdvisorMandatoryOrOptional;
            nomination.awardLevel = this.awardLevel;
            nomination.awardTitle = this.awardTitle;
            nomination.awardMessage = this.awardMessage;
            nomination.messageToApprover = this.messageToApprover;
            nomination.confirmationMessage = this.confirmationMessage;
            return nomination.placeNomination(driver);
        }
    }

    public NominationService() {
    }

    private Nomination placeNomination(WebDriver driver) throws InterruptedException{
        LandingPage landingPage = new LandingPage(driver);
        switch(nominateFrom){
            case "My Team" :
                nomination =
                        landingPage.openMyTeam()
                        .openDirectReportIndividualViewByName(recipient)
                        .recognize();
                nomination.navigateToNextStep();
                recipient = null;
                break;
            case "Menu" :
                nomination = landingPage.startNomination();
                break;
            default : throw new IllegalArgumentException("Invalid place to start nomination: " + awardAdvisorMandatoryOrOptional + "possible arguments: My Team, Menu");
        }
        if (recipient != null){
            nomination.selectRecipientBySearch(recipient);
            nomination.navigateToNextStep();
        }
        if (awardProgram != null){
            nomination.chooseAwardProgramByName(awardProgram);
        }
        if (awardReason != null){
            nomination.chooseAwardReasonByName(awardReason);
        }
        if (awardAdvisorMandatoryOrOptional != null){
            switch(awardAdvisorMandatoryOrOptional){
                case "Optional" : nomination.skipAwardAdvisor();
                break;
                default : throw new IllegalArgumentException("Invalid argument for award advisor step: " + awardAdvisorMandatoryOrOptional + "possible arguments: Mandatory, Optional");
            }
        }
        if (awardLevel != null){
            nomination.chooseAwardLevel(awardLevel);
        }
        if (awardTitle !=null){
            nomination.setTitle(awardTitle);
        }
        if (awardMessage != null){
            nomination.setMessage(awardMessage);
        }
        if (messageToApprover != null){
            nomination.setMessageToApprover(messageToApprover);
        }
        nomination.createNomination();
        if (confirmationMessage != null) {
            Assert.assertEquals(nomination.getConfirmationMessage(), confirmationMessage, "Something goes wrong, confirmation message is not displayed");
        }
        nomination.closeNominationConfirmation();
        return nomination;
    }
}
