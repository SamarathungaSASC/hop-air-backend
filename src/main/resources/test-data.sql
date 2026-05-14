-- ============================================================
-- HOP Air Platform - Test Data
-- Run AFTER hopair_schema.sql
-- ============================================================

USE hopair_db;

-- Agencies
INSERT INTO agency (name) VALUES
('City Health'),
('Metro Medical');

-- Branches
INSERT INTO branch (name, ccn, agency_id) VALUES
('City Health - Downtown', 'CCN-CH-001', 1),
('City Health - Uptown',   'CCN-CH-002', 1),
('Metro Medical - East',   'CCN-MM-001', 2);

-- Users
-- NOTE: auth0_sub is populated automatically on first login via Auth0
-- These emails must match what you register in Auth0
INSERT INTO user (email, full_name, role, agency_id, branch_id) VALUES
('superadmin@hopair.dev',   'Super Admin',      'SUPERADMIN', NULL, NULL),
('educator@cityhealth.dev', 'Alice Educator',   'EDUCATOR',   1,    NULL),
('educator2@metro.dev',     'Bob Educator',     'EDUCATOR',   2,    NULL),
('clinician@cityhealth.dev','Carol Clinician',  'CLINICIAN',  1,    1),
('trainee@cityhealth.dev',  'Dave Trainee',     'TRAINEE',    1,    1),
('clinician2@metro.dev',    'Eve Clinician',    'CLINICIAN',  2,    3),
('trainee2@metro.dev',      'Frank Trainee',    'TRAINEE',    2,    3);

-- Courses (created_by = educator user id = 2)
INSERT INTO course (name, description, target_role, branch_id, created_by) VALUES
('Basic Clinical Procedures', 'Introduction to clinical procedures for new staff', 'CLINICIAN', 1, 2),
('Trainee Orientation',       'Orientation program for new trainees',              'TRAINEE',   1, 2),
('Advanced Clinical Skills',  'Advanced skills for experienced clinicians',        'CLINICIAN', 3, 3);

-- Lessons (YouTube embed URLs as video_url)
INSERT INTO lesson (title, video_url, course_id, position) VALUES
('Introduction to Clinical Procedures', 'https://www.youtube.com/embed/dQw4w9WgXcQ', 1, 1),
('Hand Hygiene Protocol',               'https://www.youtube.com/embed/dQw4w9WgXcQ', 1, 2),
('Trainee Welcome',                     'https://www.youtube.com/embed/dQw4w9WgXcQ', 2, 1),
('Safety Guidelines',                   'https://www.youtube.com/embed/dQw4w9WgXcQ', 2, 2),
('Advanced Wound Care',                 'https://www.youtube.com/embed/dQw4w9WgXcQ', 3, 1);
