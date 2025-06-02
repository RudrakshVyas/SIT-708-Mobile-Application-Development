from flask import Flask, request, jsonify
import ollama

app = Flask(__name__)

@app.route('/chat', methods=['POST'])
def chat():
    data = request.get_json()
    user_msg = data.get("message")

    if not user_msg:
        return jsonify({"response": "No message received"}), 400

    response = ollama.chat(
        model='llama2',
        messages=[{"role": "user", "content": user_msg}]
    )

    return jsonify({"response": response["message"]["content"]})

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000)
