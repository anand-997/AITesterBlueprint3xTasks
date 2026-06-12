# n8n Task — Generate Test Artifacts from a PRD

## Goal

Build an n8n workflow that takes a **Product Requirements Document (PRD)** as input
and automatically produces a test plan, test cases, and ready to run Playwright scripts.

## Input

- A PRD file in **PDF/.text/.md/PlainText** format. (mandatory)
- Figma links/Screenstots (optional)
- Application URL (optionla)
  - Example: the Cloud Version PRD for **VWO** (`https://app.vwo.com/`), ~145 KB. (dont mention anywere VWO it is just a sample)

## Workflow Steps

1. **Consume the Requirement** — read and extract the requirements from the PRD.
2. **Generate the test plan and test strategy** — based on the extracted requirements.
3. **Generate the test cases** — based on the test plan and strategy.
4. **Generate the Playwright scripts** — based on the test cases make sure it should be ready to use.

## Outputs

| File | Description |
|------|-------------|
| `test_plan.md` | The test plan derived from the PRD. |
| `test_strategy.md` | The test strategy derived from the PRD. |
| `test_cases_<feature_name>.csv` | All test cases derived from the test plan and strategy. for featurename derive from the PRD if it is a module or feature or application|
| `playwright_test_cases.md` | All Playwright spec files and test cases derived from the test plan and strategy. |

The `playwright_test_cases.md` file must contain **all spec files and all test cases**,
covering both **valid** and **invalid** scenarios for provided requirement.

## Application Under Test

- **URL:** `https://app.vwo.com/`
- **Login page** → leads to the **Dashboard**.
  - **Valid login** → user is taken to the Dashboard page.
  - **Invalid login** → user sees an error message.


completed: https://www.sdetclub.com/c/ai-tester-3x-only/task-7th-june-2026-stlc-agent-n8n#comment_wrapper_106015099