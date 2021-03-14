FIRST_VALUE = "○"
SECOND_VALUE = "x"
board = [
    {"key": "a", "row": 1, "col": 1, "value": "a"},
    {"key": "b", "row": 1, "col": 2, "value": "b"},
    {"key": "c", "row": 1, "col": 3, "value": "c"},
    {"key": "d", "row": 2, "col": 1, "value": "d"},
    {"key": "e", "row": 2, "col": 2, "value": "e"},
    {"key": "f", "row": 2, "col": 3, "value": "f"},
    {"key": "g", "row": 3, "col": 1, "value": "g"},
    {"key": "h", "row": 3, "col": 2, "value": "h"},
    {"key": "i", "row": 3, "col": 3, "value": "i"},
]


def main() -> None:
    is_first_move: bool = True
    while True:
        to_string()
        next_user: str = FIRST_VALUE if is_first_move else SECOND_VALUE
        print("次は{}の番です".format(next_user))
        input_value: str = correct_input()
        # 精査: 入力箇所が未入力であること確認
        if is_not_default(input_value):
            print("すでに指定済です。別の場所を指定してください")
            continue
        # 入力値を盤面に反映
        target = next((i for i in board if i["key"] == input_value), None)
        target["value"] = FIRST_VALUE if is_first_move else SECOND_VALUE
        # 縦、横、斜めが揃ったかチェック
        is_finish: bool = is_tate() or is_yoko() or is_naname()
        # 揃ったら終了
        if is_finish:
            winner: str = FIRST_VALUE if is_first_move else SECOND_VALUE
            print("終了！！{}の勝ち！！".format(winner))
            break
        else:
            # 次の人へ
            is_first_move = not is_first_move


def to_string() -> None:
    print('''
 -----------
| {} | {} | {} |
|---+---+---|
| {} | {} | {} |
|---+---+---|
| {} | {} | {} |
 -----------
'''.format(
        board[0]["value"],
        board[1]["value"],
        board[2]["value"],
        board[3]["value"],
        board[4]["value"],
        board[5]["value"],
        board[6]["value"],
        board[7]["value"],
        board[8]["value"]
    ))


def correct_input() -> str:
    return_value: str = ""
    while True:
        value: str = input()
        if value in ["a", "b", "c", "d", "e", "f", "g", "h", "i"]:
            return_value = value
            break
        elif value == "q":
            print("終～了～")
            exit()
        else:
            print("a ~ i のいずれかで指定してください")
            continue
    return return_value


def is_not_default(key: str) -> bool:
    return next((i["value"] for i in board if i["key"] == key), None) in (FIRST_VALUE, SECOND_VALUE)


def is_tate() -> bool:
    is_finish = False
    for i in range(1, 4):
        is_finish = all([b["value"] == FIRST_VALUE for b in board if b["col"] == i]) \
            or all([b["value"] == SECOND_VALUE for b in board if b["col"] == i])
        if is_finish:
            break
    return is_finish


def is_yoko() -> bool:
    is_finish = False
    for i in range(1, 4):
        is_finish = all([b["value"] == FIRST_VALUE for b in board if b["row"] == i]) \
            or all([b["value"] == SECOND_VALUE for b in board if b["row"] == i])
        if is_finish:
            break
    return is_finish


def is_naname() -> bool:
    migi_naname = [board[0], board[4], board[8]]
    hidari_naname = [board[2], board[4], board[6]]
    return all(i["value"] == FIRST_VALUE for i in migi_naname) \
        or all(i["value"] == SECOND_VALUE for i in migi_naname) \
        or all(i["value"] == FIRST_VALUE for i in hidari_naname) \
        or all(i["value"] == SECOND_VALUE for i in hidari_naname)


main()
