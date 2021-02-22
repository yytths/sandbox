# TIPs

## portがすでに使われているエラーが出たとき

以前実行したプロセスがゾンビとなっているかも

cmdから `taskkill` を試す

```
> netstat -ano | find ":8080" | find "LISTENING"
  TCP         0.0.0.0:8080           0.0.0.0:0              LISTENING       9496
  TCP         [::]:8080              [::]:0                 LISTENING       9496

> tasklist | find "9496"
java.exe                      9496 Console                    1    186,120 K

> taskkill /pid 9496 /F
成功: PID 9496 のプロセスは強制終了されました。
```