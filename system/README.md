# system

- activity の lifecycle
  - [アクティビティのライフサイクルについて  |  Android デベロッパー  |  Android Developers](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ja)
  - 表示したとき: onCreate, onStart, onResume
  - バックグラウンドになったとき(一覧表示でも同じ): onPause, onStop
    - 戻ってきたとき: onStart, onResume
    - そのままタスクキルしたとき: onDestroy
  - 回転したとき: onPause, onStop, onDestroy, onCreate, onStart, onResume
  - 他のアクティビティに移動したとき: onPause, onStop, onDestroy
