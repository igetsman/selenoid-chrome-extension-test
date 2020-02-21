Feature: Browser extensions

  Scenario: 01 - Simple test extension
    Given Opened page "chrome-extension://ibhgedghlmjcnkomonjfejfiobaphlkp/popup.html"
    Then Content with "TRANSLATE THIS PAGE" visible

