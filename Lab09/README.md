## Lab Exercises  

### Lab 9 - 01
<b>Requirements:</b>
- Working with AsynTask

<b>Output:</b>

<img width="400" src="https://github.com/cminhho/TDTU-LapTrinhUngDungDiDong/blob/master/Lab09/screenshots/exercise_01.png" alt="Working with AsynTask"/>


### Lab 9 - 02
<b>Requirements:</b>
- Working with Service + AsynTask

<b>Output:</b>

<img width="400" src="https://github.com/cminhho/TDTU-LapTrinhUngDungDiDong/blob/master/Lab09/screenshots/exercise_02.png" alt="Working with Service + AsynTask"/>

### Lab 9 - 03
<b>Requirements:</b>
- Working with IntentService + AsynTask

<b>Output:</b>

<img width="400" src="https://github.com/cminhho/TDTU-LapTrinhUngDungDiDong/blob/master/Lab09/screenshots/exercise_03.png" alt="Working with Service + AsynTask"/>


## Questions/Anwsers
### Question: AsyncTask vs IntentService vs Service?
### Anwsers
<b>Service</b> 
- Runs in the application’s main thread.
- Performs a longer-running operation.

<b>IntentService</b> 
- Handles a single worker thread, only one request will be processed at a time.
- Stops itself when it runs out of work. 
- Performs a longer-running operation .

<b>AsyncTask</b>
- Runs on a background thread and whose result is published on the UI thread.
- Used for short operations (a few seconds at the most.)

## References:
- https://developer.android.com/reference/android/os/AsyncTask
- https://developer.android.com/reference/android/app/Service
- https://developer.android.com/reference/android/app/IntentService
