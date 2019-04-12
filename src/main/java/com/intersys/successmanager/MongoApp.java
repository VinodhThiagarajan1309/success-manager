package com.intersys.successmanager;

import com.intersys.successmanager.domain.review.ReviewCycle;
import com.intersys.successmanager.domain.template.Question;
import com.intersys.successmanager.domain.template.QuestionsOptions;
import com.intersys.successmanager.domain.template.Template;
import com.intersys.successmanager.domain.user.Answer;
import com.intersys.successmanager.domain.user.SurveyResponse;
import com.intersys.successmanager.domain.user.User;
import com.intersys.successmanager.domain.user.UserReviewCycle;
import com.mongodb.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.*;

public class MongoApp {

    private static final Log log = LogFactory.getLog(MongoApp.class);

    public static void main(String[] args) throws Exception {

        MongoOperations mongoOps = new MongoTemplate(
                new SimpleMongoDbFactory(new MongoClient(),
                        "success_management"));

        /**
         * Create a User
         */
        User createdUser = createUser(mongoOps);

        /* Create Template Action Begins */

        /**
         * Create a 3 questions 2 radio based and 1 text based
         */

        Question question1 = createRadioQuestion("Do you Work from home ?","Always", "Never", "Occasional");
        Question question2 = createRadioQuestion("What color do you like ?","Blue", "Green", "Red");
        Question question3 = createTextQuestion();

        /**
         * Create a Template Now
         */
        Template template = createTemplate(question1, question2, question3);
        mongoOps.insert(template);

        /* Create Template Action Ends */



        /* Create Review Cycle Action Begins*/

        /**
         * Create a Review Cycle
         */
        ReviewCycle reviewCycle = createReviewCycle();
        mongoOps.insert(reviewCycle);

        /* Create Review Cycle Action Ends*/

        /**
         *
         *
         *
         * Assume that we have assigned the above template to the created user
         * during the Assign Process.
         *
         *
         *
         *
         */

        /**
         * Assign the Review cycle and the Template chosen for the user
         */

        UserReviewCycle userReviewCycle = UserReviewCycle.builder()
                .reviewCycle(reviewCycle)
                .template(template)
                .userSubmitted(false)
                .managerReviewed(false)
                .build();

        /**
         * Assign the UserReviewCycle to the User Object
         */
        createdUser.setUserReviewCycleList(Arrays.asList(userReviewCycle));
        /**
         * Update the User
         */
        mongoOps.save(createdUser);


        /**
         * At this Stage the User has not submitted the Response yet
         */




        /* Employee Creates Response Begins */

        /**
         * Employee submits response
         */

        String question1UUID = question1.getQuestionId();
        String question2UUID = question2.getQuestionId();
        String question3UUID = question3.getQuestionId();

        SurveyResponse surveyResponse = SurveyResponse.builder()
                .answerList(Arrays.asList(
                        createRadioAnswer(question1UUID, "Never", "Answered Question 1"),
                        createRadioAnswer(question2UUID, "Blue", "Answered Question 2"),
                        createTextAnswer(question3UUID)
                ))
                .build();

        /* Employee Creates Response Ends */


        /**
         * User Submits or Saves the response
         */

        createdUser.getUserReviewCycleList().get(0).setSurveyResponse(surveyResponse);
        createdUser.getUserReviewCycleList().get(0).setUserSubmitted(true);


        /**
         * Update the User
         */
        mongoOps.save(createdUser);


    }

    private static Answer createTextAnswer(String question1UUID) {
        return Answer.builder()
                .questionId(question1UUID)
                .commentedText("Answered Question 3")
                .managerComments("")
                .managerSelectedChoice("")
                .build();
    }

    private static Answer createRadioAnswer(String question1UUID, String never, String s) {
        return Answer.builder()
                .questionId(question1UUID)
                .selectedChoice(never)
                .commentedText(s)
                .managerComments("")
                .managerSelectedChoice("")
                .build();
    }

    private static Template createTemplate(Question question1, Question question2, Question question3) {
        return Template.builder()
                .templateId(UUID.randomUUID().toString())
                .templateName("SENIOR CONSULTANT")
                .templateAuthor(UUID.randomUUID().toString())
                .questions(Arrays.asList(question1, question2, question3))
                .build();
    }

    private static Question createTextQuestion() {
        return Question.builder()
                .questionId(UUID.randomUUID().toString())
                .questionType("text")
                .questionText("What was your contribution ?")
                .build();
    }

    private static Question createRadioQuestion(String questionText, String always, String never, String occasional) {
        return Question.builder()
                .questionId(UUID.randomUUID().toString())
                .questionType("radio")
                .questionText(questionText)
                .questionsOptions(Arrays.asList(
                        QuestionsOptions.builder().optionName(always).build(),
                        QuestionsOptions.builder().optionName(never).build(),
                        QuestionsOptions.builder().optionName(occasional).build()
                ))
                .build();
    }

    private static ReviewCycle createReviewCycle() {
        return ReviewCycle.builder()
                .cycleId(UUID.randomUUID().toString())
                .cycleName("2019_FIRST_HALF")
                .evalStartDate(new Date())
                .evalEndDate(new Date())
                .cycleKickDate(new Date())
                .cycleEndDate(new Date())
                .cycleAuthor(UUID.randomUUID().toString())
                .build();
    }

    private static User createUser(MongoOperations mongoOps) {
        return mongoOps.insert(User.builder()
                .userId(UUID.randomUUID().toString())
                .emailId("vinodh.thiagarajan@gmail.com")
                .managerId(UUID.randomUUID().toString())
                .name("Vinodh Thiagarajan")
                .build());
    }
}