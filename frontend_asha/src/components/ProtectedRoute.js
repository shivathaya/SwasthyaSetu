import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);

  useEffect(() => {
    // Check if the sessionStorage has a session flag
    const session = sessionStorage.getItem("isAuthenticated");

    if (session) {
      setIsAuthenticated(true);  // Session exists in sessionStorage
    } else {
      setIsAuthenticated(false); // No session flag found
    }
  }, []);

  if (isAuthenticated === null) {
    return <div>Loading...</div>;  // Show loading while checking authentication status
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" />;  // Redirect to login if no session
  }

  return children;  // If session is valid, render the protected children
};

export default ProtectedRoute;
