# Tutoring App

This project is an Android-based tutoring app designed to facilitate tutor-student matching and provide a convenient platform for personalized learning. The app allows students to find and connect with tutors, book lessons, and evaluate tutors, while tutors can create courses and manage their schedules efficiently.

## Features

### For Students:
1. **Sign-up/Login**:
   - Register as a student using email, username, and password.
   - Login to access personalized features.

2. **Search for Tutors**:
   - Search by subject or tutor name.
   - View tutors' profiles, including their schedules, ratings, and certificates.

3. **Tutor Evaluation**:
   - Rate and review tutors based on their performance.
   - Ratings and reviews are displayed on tutors' profiles.

4. **Schedule Management**:
   - View upcoming courses and lesson details.

### For Tutors:
1. **Sign-up/Login**:
   - Register as a tutor using email, username, and password.
   - Provide additional details such as teaching experience, certificates, and self-introduction.

2. **Course Creation**:
   - Create new courses by specifying time, subject, and student details.
   - Add courses to both tutor and student schedules.

3. **Schedule Management**:
   - View and manage upcoming lessons.

## Technical Details

- **Development Environment**: Android Studio
- **Database**: SQLite
- **UI Design**: Figma
- **Programming Language**: Java

### Database Structure
The app uses SQLite as the backend database. Key tables include:
- `user`: Stores basic user information (students and tutors).
- `tutor`: Stores additional tutor-specific information.
- `student`: Stores additional student-specific information.
- `schedule`: Manages course schedules for tutors and students.
- `comment` and `score`: Handle tutor reviews and ratings.
- `message`: Stores chat messages between tutors and students.
