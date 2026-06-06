# Task — Test Strategy Buddy

**Date:** 6th June 2026

## Overview

Build an application called **Test Strategy Buddy** that automatically generates a
test strategy from a feature requirement.

The input can be any of the following:

- A **Jira ID**
- A **plain-text requirement or attachment** (`.txt` or `.md` file)

The input describes a feature — for example, *"Create a dummy test feature for a
login page or dashboard page."* From this requirement, the app generates a complete
test strategy.

## Requirements

### 1. Input & Generation
- Fetch the requirement from a **Jira ID** or a **plain-text / `.txt` / `.md` file**.
- Parse the feature details from the input.
- Generate a **test strategy** that follows the provided template format.

### 2. User Interface
- Build a UI for the application.
- The UI **must support both dark mode and light mode**.

### 3. Template
Use the test strategy format defined in the template below:

- **Template:** https://drive.google.com/drive/folders/11eAx342NHP1NGiqD_yQMAqfkZkbIjzNR (Already added the template in the TEST_STRATEGY_TEMPLATE.md file)

### 4. Deployment
- Push the complete project to **GitHub**.
- Deploy the application to **Vercel**.

## Deliverables

Submit the following:

1. **GitHub repository link**
2. **Vercel deployment link**
3. **Screenshot of the Vercel deployment**
