<?php

// Database connection details
$server = "localhost";
$username = "root";
$password = ""; // Replace with your actual password
$database = "studentdb";

// Create a new MySQLi object
$conn = new mysqli($server, $username, $password, $database);

// Check for connection errors
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if the request method is POST
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Retrieve the name and address from the request body
    $name = $_POST["name"];
    $address = $_POST["address"];

    // Prepare and bind the SQL statement using prepared statements
    $stmt = $conn->prepare(
        "INSERT INTO students (
            name,
            address
            ) VALUES (?, ?)");
    $stmt->bind_param("ss", $name, $address);

    // Execute the prepared statement and check for errors
    if ($stmt->execute()) {
        $response = array(
            "success" => true,
            "message" => "Data inserted successfully"
            );
        echo json_encode($response);
    } else {
        $response = array(
            "success" => false,
            "message" => "Failed to insert data"
        );
        echo json_encode($response);
    }

    // Close the statement
    $stmt->close();
}

// Close the database connection
$conn->close();