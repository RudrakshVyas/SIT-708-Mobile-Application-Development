package com.example.llamabotapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editMessage;
    private Button btnSend;

    private ChatAdapter adapter;
    private List<ChatMessage> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerView);
        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);

        chatList = new ArrayList<>();
        adapter = new ChatAdapter(chatList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            String message = editMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                // Add user message to chat
                chatList.add(new ChatMessage("user", message));
                adapter.notifyItemInserted(chatList.size() - 1);
                editMessage.setText("");


                fetchBotResponse(message);
            }
        });
    }

    private void fetchBotResponse(String userMessage) {
        ChatApiService apiService = RetrofitClient.getClient().create(ChatApiService.class);
        ChatRequest request = new ChatRequest(userMessage);

        apiService.sendMessage(request).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    chatList.add(new ChatMessage("bot", response.body().getResponse()));
                } else {
                    chatList.add(new ChatMessage("bot", "Error: Invalid response"));
                }
                adapter.notifyItemInserted(chatList.size() - 1);
                recyclerView.scrollToPosition(chatList.size() - 1);
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                chatList.add(new ChatMessage("bot", "Error: " + t.getMessage()));
                adapter.notifyItemInserted(chatList.size() - 1);
                recyclerView.scrollToPosition(chatList.size() - 1);
            }
        });
    }

}

