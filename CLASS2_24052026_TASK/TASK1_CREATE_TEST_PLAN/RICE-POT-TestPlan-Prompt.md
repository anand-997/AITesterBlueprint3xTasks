# RICE-POT Prompt: Test Plan Generator

Copy everything inside the code block below and paste it into your AI tool to generate a complete test plan.

```
### R — Role
You are a Senior QA Lead with 15+ years of experience in API testing, test planning, and software quality assurance. You have deep expertise in REST API testing, Postman, REST Assured automation, and enterprise-level test documentation.

### I — Instructions
1. Generate a complete, professional Test Plan document for the provided API or application.
2. The test plan MUST include all of the following sections in this exact order:
   - Objective
   - Scope (list all testing types as numbered items)
   - Inclusions (all CRUD operations and testing types relevant to the system)
   - Test Environments (OS, browsers, devices, network, environment table with Name and Env URL)
   - Defect Reporting Procedure (criteria, steps, triage process, tools, roles, POC table)
   - Test Strategy (Step 1: test case design techniques, Step 2: testing procedure, Step 3: best practices)
   - Test Schedule (task table with task name and dates, sprint duration)
   - Test Deliverables
   - Entry and Exit Criteria (for Requirement Analysis, Test Execution, and Test Closure phases)
   - Tools (list of tools used)
   - Risks and Mitigations (at least 3 risk/mitigation pairs)
   - Approvals (list of document types requiring client approval)
3. For the Scope section, include at minimum: Functional, Data Validation, Error Handling, Performance, Security, Integration, Compatibility, Documentation Review, Load, Regression, Edge Case, Concurrency, Ad Hoc, Usability, CI/CD, Performance Monitoring, Backup & Recovery, Internationalization, Rate Limiting, and Third-Party Integration testing.
4. For the Inclusions section, cover: Create (POST), Read (GET), Update (PUT), Delete (DELETE), Boundary, Concurrency, Data Validation, Authentication & Authorization, Error Handling, Security, Performance, Integration, Regression, Documentation Review, Load, Compatibility, Usability, CI/CD, Rate Limiting, Backup & Recovery.
5. Write the Defect Reporting Procedure with a POC table listing Frontend, Backend, and DevOps contacts.
6. Write the Test Strategy across 3 steps: (1) test design techniques including ECP, BVA, Decision Table, State Transition, Use Case, Error Guessing, Exploratory; (2) testing procedure from smoke testing through regression; (3) best practices including Context Driven, Shift Left, Exploratory, and End-to-End Flow Testing.
7. Write the Test Schedule as a task list (Creating Test Plan, Test Case Creation, Test Case Execution, Summary Reports Submission) with a sprint duration note.
8. Entry and Exit Criteria must cover three phases: Requirement Analysis, Test Execution, and Test Closure — each with explicit Entry and Exit conditions.

Do NOT:
- Invent API endpoints, error codes, or behavior not derivable from the provided context.
- Skip any of the 12 required sections.
- Add sections not listed above.
- Use placeholder text like "TBD" without noting it is intentionally left for the team to fill in.
- Write the test plan in a bullet-only format — use descriptive prose paragraphs for explanatory sections (Objective, Defect Reporting Procedure, Test Strategy) and structured lists/tables where appropriate.

### C — Context
- System under test: [INSERT API or APPLICATION NAME AND DESCRIPTION HERE]
- API documentation URL: [INSERT URL]
- The system supports booking management operations including creating, reading, updating, and deleting bookings, plus authentication token generation.
- The application is known to contain bugs — part of the objective is to discover them.
- Test execution will use Postman for manual/exploratory runs and REST Assured (Java) for automation.
- Team uses JIRA for bug tracking and follows a sprint-based delivery model.
- Environment table should include at least QA and Pre-Prod rows.
- Supported platforms: Windows 10 (Chrome, Firefox, Edge), macOS (Safari), Android (Chrome), iOS Safari.
- Defect POC contacts — fill in real names or use these placeholders: Frontend: [Frontend Dev Name], Backend: [Backend Dev Name], DevOps: [DevOps Name].

### E — Example
The Objective section should read like this:

"The objective of this test plan is to verify the [System Name] API, covering booking creation, update, deletion, and authentication. The system is known to contain defects which must be identified and logged. All test cases will be executed in Postman, and automation will be implemented using the REST Assured framework."

The Scope section should list testing types as numbered items with 2–4 bullet sub-points each, for example:
"1. Functional Testing:
   - Verify the correctness and functionality of all API endpoints as per the API documentation.
   - Test various scenarios for booking creation, modification, and cancellation.
   - Validate user authentication and authorization mechanisms for protected endpoints."

The Test Environments table should look like:
| Name      | Env URL                                         |
|-----------|-------------------------------------------------|
| QA        | https://[app-url]/apidoc/index.html             |
| Pre Prod  | https://[app-url]/apidoc/index.html             |

The Defect POC table should look like:
| Defect Process | POC       |
|----------------|-----------|
| Frontend       | [Name]    |
| Backend        | [Name]    |
| Dev Ops        | [Name]    |

### P — Parameters
- Output must be deterministic: same input context must produce the same section structure every time.
- Every statement must be traceable to the provided context or standard QA industry practice.
- If a detail (e.g., sprint dates, team names) is not provided, leave a clearly marked placeholder: [INSERT VALUE].
- Do not invent features, integrations, or behaviors not stated in the context.
- Do not infer default "typical" system behavior unless it is a universal REST API convention (e.g., HTTP status codes).
- Each section must be complete — do not abbreviate or truncate with "...and more."
- The document must be production-quality, ready to present to a client for sign-off.
- Minimum length: the Scope section must cover at least 20 testing types; Inclusions must cover at least 19 CRUD-related testing areas.

### O — Output
- Format: Markdown (.md)
- Structure: Sections in this exact order — Objective, Scope, Inclusions, Test Environments, Defect Reporting Procedure, Test Strategy, Test Schedule, Test Deliverables, Entry and Exit Criteria, Tools, Risks and Mitigations, Approvals.
- Use `##` for top-level section headings and `###` for sub-headings (e.g., Entry Criteria / Exit Criteria within each phase).
- Use numbered lists for Scope and Inclusions items with bullet sub-points.
- Use Markdown tables for: Test Environments, Defect POC, and Test Schedule.
- Prose paragraphs for: Objective, Defect Reporting Procedure narrative, Test Strategy steps, and Approvals.
- Do not add a table of contents — output the sections directly.
- Do not wrap the output in a code block — output raw Markdown.

### T — Tone
Technical and professional. Formal QA enterprise documentation style. Write as if this document will be reviewed and signed off by a client stakeholder. No casual language, no first-person ("I"), no hedging phrases ("might," "could possibly"). Use declarative statements throughout.
```

---

## How to Use This Prompt

1. Copy the entire block above (everything between the triple backticks).
2. Replace the `[INSERT ...]` placeholders in the **C — Context** section with your actual application details.
3. Paste into Claude (or any capable AI tool).
4. The output will be a complete Markdown test plan matching the structure of `STRUCTURE.md`.

---

## RICE-POT Framework Reference

| Letter | Component    | Purpose in this prompt                                              |
|--------|--------------|---------------------------------------------------------------------|
| **R**  | Role         | Senior QA Lead — sets the expertise level and authority of voice   |
| **I**  | Instructions | 8 ordered steps + explicit "Do NOT" rules to prevent hallucination |
| **C**  | Context      | System under test, tools, team, environments, platforms            |
| **E**  | Example      | Sample Objective paragraph and table formats to anchor the style   |
| **P**  | Parameters   | Determinism, no hallucination, placeholder policy, length minimums |
| **O**  | Output       | Exact Markdown format, section order, heading levels, table usage  |
| **T**  | Tone         | Formal, declarative, enterprise-grade documentation style          |
