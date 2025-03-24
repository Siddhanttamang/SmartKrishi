import os
import requests
from flask import Flask, request, jsonify
from flask_cors import CORS
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

app = Flask(__name__)
CORS(app)  # Enable CORS for all domains (adjust for production)

# Configuration
API_KEY = os.getenv("OPENWEATHER_API_KEY")
BASE_URL = "https://api.openweathermap.org/data/2.5/weather"
DEFAULT_CITY = "Kathmandu"

def fetch_weather(city):
    """Fetch weather data from OpenWeather API."""
    try:
        response = requests.get(
            BASE_URL,
            params={"q": city, "appid": API_KEY, "units": "metric"},
            timeout=10
        )
        response.raise_for_status()  # Raise exception for HTTP errors

        data = response.json()
        return {
            "city": data.get("name"),
            "temperature": data["main"].get("temp"),
            "humidity": data["main"].get("humidity"),
            "weather": data["weather"][0].get("description"),
        }
    
    except requests.exceptions.HTTPError as e:
        return {"error": "City not found"} if response.status_code == 404 else {"error": "Weather API error"}
    except requests.exceptions.Timeout:
        return {"error": "Request timeout"}
    except requests.exceptions.RequestException:
        return {"error": "Network error"}
    except (KeyError, ValueError):
        return {"error": "Invalid response format"}

@app.route('/weather', methods=['GET'])
def get_weather():
    """API route to fetch weather details."""
    city = request.args.get("city", DEFAULT_CITY).strip()
    
    if not city or len(city) > 100:
        return jsonify({"error": "Invalid city parameter"}), 400

    weather_data = fetch_weather(city)
    if "error" in weather_data:
        return jsonify(weather_data), 400

    return jsonify(weather_data)

@app.errorhandler(500)
def internal_error(error):
    """Handle internal server errors."""
    return jsonify({"error": "Internal server error"}), 500

if __name__ == "__main__":
    print("🚀 Server running at http://127.0.0.1:5000")
    app.run(host="0.0.0.0", port=5000, debug=os.getenv("FLASK_DEBUG", False))
