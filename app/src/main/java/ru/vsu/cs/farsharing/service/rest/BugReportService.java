package ru.vsu.cs.farsharing.service.rest;


import retrofit2.Call;
import retrofit2.http.POST;
import ru.vsu.cs.farsharing.model.request.MessageRequest;

public interface BugReportService {
    @POST("api/message")
    Call<Void> sendBugReportMessage(MessageRequest message);
}
