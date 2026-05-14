-- ============================================================
-- HOP Air Platform - Database Schema
-- Run this FIRST before test-data.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS hopair_db;
USE hopair_db;

CREATE TABLE IF NOT EXISTS agency (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS branch (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ccn VARCHAR(100) NOT NULL UNIQUE,
    agency_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_branch_agency FOREIGN KEY (agency_id) REFERENCES agency(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255),
    role ENUM('SUPERADMIN', 'EDUCATOR', 'CLINICIAN', 'TRAINEE') NOT NULL,
    agency_id BIGINT,
    branch_id BIGINT,
    auth0_sub VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_agency FOREIGN KEY (agency_id) REFERENCES agency(id) ON DELETE SET NULL,
    CONSTRAINT fk_user_branch FOREIGN KEY (branch_id) REFERENCES branch(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    target_role ENUM('CLINICIAN', 'TRAINEE') NOT NULL,
    branch_id BIGINT NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_course_branch FOREIGN KEY (branch_id) REFERENCES branch(id) ON DELETE CASCADE,
    CONSTRAINT fk_course_creator FOREIGN KEY (created_by) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS lesson (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    video_url VARCHAR(500),
    course_id BIGINT NOT NULL,
    position INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_lesson_course FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Create MySQL user with restricted permissions
-- Run as root: CREATE USER IF NOT EXISTS 'hopair_user'@'localhost' IDENTIFIED BY 'fkfkf';
-- GRANT ALL PRIVILEGES ON hopair_db.* TO 'hopair_user'@'localhost';
-- FLUSH PRIVILEGES;
