//package ru.appline.framework.utils;
//
//import io.cucumber.plugin.event.EventPublisher;
//import io.cucumber.plugin.event.Status;
//import io.cucumber.plugin.event.TestStepFinished;
//import io.qameta.allure.Allure;
//import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import ru.appline.framework.managers.DriverManager;
//
//public class MyAllureListener extends AllureCucumber5Jvm {
//
//    public static byte[] addScreenshot() {
//        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//
//    public void setEventPublisher(EventPublisher eventPublisher) {
//        eventPublisher.registerHandlerFor(TestStepFinished.class, event -> {
//            if (event.getResult().getStatus().is(Status.FAILED)) {
//                Allure.getLifecycle().addAttachment("screenshot", "image/png", "png", addScreenshot());
//            }
//        });
//        super.setEventPublisher(eventPublisher);
//    }
//}
