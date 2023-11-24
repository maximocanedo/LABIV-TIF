REM BATCH Simple que genera un index.js mapeando todos los exports de los .js presentes.
@echo off
del index.js
@echo 'use strict'; >> index.js

for %%I in (*.js) do (
    if /I not "%%~nxI"=="index.js" (
        echo import %%~nI from './%%~nxI'; >> index.js
    )
)
@echo. >> index.js
@echo export { >> index.js
set first=1
for %%I in (*.js) do (
    if /I not "%%~nxI"=="index.js" (
        if defined first (
            echo %%~nI >> index.js
            set "first="
        ) else (
            echo ,%%~nI >> index.js
        )
    )
)

@echo }; >> index.js
