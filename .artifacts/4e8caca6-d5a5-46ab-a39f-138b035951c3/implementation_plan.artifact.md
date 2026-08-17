# Fix XML Syntax Errors in activity_main.xml

The `activity_main.xml` file contains XML syntax errors caused by comments placed inside tag declarations. XML does not allow comments between the `<` and `>` of a tag.

## Proposed Changes

### app/src/main/res/layout/activity_main.xml

#### [MODIFY] [activity_main.xml](file:///D:/Work/Apps/PrepMate/app/src/main/res/layout/activity_main.xml)
- Move comments outside of the tags to ensure valid XML syntax.
- Ensure all tags are correctly opened and closed.

## Verification Plan

### Automated Tests
- Use `analyze_file` to verify that there are no more syntax errors in `activity_main.xml`.

### Manual Verification
- Deploy the app to a device to ensure the layout renders correctly.
