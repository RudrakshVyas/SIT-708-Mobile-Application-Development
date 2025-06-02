from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

API_URL = "https://api-inference.huggingface.co/models/HuggingFaceH4/zephyr-7b-beta"
HF_TOKEN = "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"

headers = {
    "Authorization": f"Bearer {HF_TOKEN}"
}

@app.route('/chat', methods=['POST'])
def chat():
    user_msg = request.json.get("message", "")
    if not user_msg:
        return jsonify({"response": "No message provided"}), 400

    payload = {
        "inputs": f"<|system|>You are a helpful assistant.<|user|>{user_msg}<|assistant|>",
        "options": {
            "wait_for_model": True
        }
    }

    response = requests.post(API_URL, headers=headers, json=payload)
    if response.status_code == 200:
        try:
            result = response.json()
            bot_reply = result[0]["generated_text"].split("<|assistant|>")[-1].strip()
            return jsonify({"response": bot_reply})
        except Exception as e:
            return jsonify({"response": "Error parsing model output"}), 500
    else:
        return jsonify({"response": f"Error: {response.status_code}"}), 500

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000)
