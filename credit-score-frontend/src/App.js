// src/App.js
import React, { useState } from "react";
import axios from "axios";
import './App.css';

function App() {
  const [userId, setUserId] = useState("");
  const [creditScore, setCreditScore] = useState(null);
  const [error, setError] = useState(null);

  const fetchCreditScore = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/creditScore/${userId}`);
      setCreditScore(response.data);
      setError(null);
    } catch (err) {
      setError("Error fetching credit score. Please try again.");
      setCreditScore(null);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Credit Score Service</h1>
        <div className="input-section">
          <input
            type="text"
            placeholder="Enter User ID"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          <button onClick={fetchCreditScore}>Get Credit Score</button>
        </div>
        
        {error && <p className="error-message">{error}</p>}
        
        {creditScore && (
          <div className="credit-score-section">
            <h2>Credit Score Details</h2>
            <p><strong>User ID:</strong> {creditScore.userId}</p>
            <p><strong>Credit Score:</strong> {creditScore.score}</p>
          </div>
        )}
      </header>
    </div>
  );
}

export default App;
