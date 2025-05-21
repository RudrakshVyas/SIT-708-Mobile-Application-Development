from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

@app.route('/chat', methods=['POST'])
def chat():
    data = request.get_json()
    user_input = data.get("message")

    ollama_response = requests.post(
        "http://localhost:11434/api/generate",
        json={
            "model": "llama2",
            "prompt": user_input,
            "stream": False
        },
        timeout=60  # Optional: increase timeout to avoid errors
    )

    return jsonify({"response": ollama_response.json()["response"]})


if __name__ == '__main__':
    app.run(port=5000)
