tests_passed=0
tests_failed=0

function test() {

    test_name=$1
    params=$2
    expected_status=$3

    ./RUN.sh "$params"
    actual_status=$?

    if [ $actual_status -eq "$expected_status" ]
    then
    ((tests_passed++))
    tput setaf 2;
    echo "$test_name passed with $actual_status (expected $expected_status)"
    tput setaf 7;
    else
    ((tests_failed++))
    tput setaf 1;
    echo "$test_name failed with $actual_status (expected $expected_status)"
    tput setaf 7;
    fi

}

test "T1.1" "" 0
test "T1.2" "-h" 0
test "T1.3" "some trash"  0
test "T1.4" "-unknown"  0
test "T1.5" "-unknown unknown"  0
test "T2.1" "-login ArtBekk -pass 3678"  0
test "T2.2" "-pass 3678 -login ArtBekk" 0
test "T2.3" "-login BekkArt -pass 3678"  3
test "T2.4" "-login ArtBekk -pass delete"  4
test "T2.5" "-login #@BekkArt!! -pass 3678"  2
test "T3.1" "-login AdamHiggs -pass 1234 -role WRITE -res AA"  0
test "T3.2" "-login AdamHiggs -pass 1234 -role DELETE -res AA"  5
test "T3.3" "-login AdamHiggs -pass 1234 -role WRITE -res CD"  6
test "T3.4" "-login ArtBekk -pass 3678 -role READ -res CD.E"  0
test "T3.5" "-login ArtBekk -pass 3678 -role WRITE -res AC.BAE.be"  0
test "T3.6" "-login ArtBekk -pass delete -role DELETE -res A"  4
test "T3.7" "-login HiggsAdam -pass 1234 -role DELETE -res A"  3
test "T3.8" "-login AdamHiggs -pass 1234 -role EXECUTE -res ZB.AA.b"  0
test "T3.9" "-login ArtBekk -pass 3678 -role READ"  0
test "T3.10" "-login ArtBekk -pass 3678 -role EXECUTE -res A"  6
test "T3.11" "-login AdamHiggs -pass 1234 -role WRITE -res ZB.A.a"  6
test "T4.1" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2020-01-01 -de 2020-02-01 -vol 20"  0
test "T4.2" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2020-01-01 -de 2020-02-01 -vol 1001"  7
test "T4.3" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2020-01-01 -de 2020-02-01 -vol -20"  7
test "T4.4" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2aa0-01-01 -de B020-d2-01 -vol 20"  7
test "T4.5" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2020-01-01 -de 2020-02-01 -vol agws"  7
test "T4.6" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2020-01-01 -de 2020-02-01" 0
test "T4.7" "-login ArtBekk -pass 3678 -role READ -res AV  -de 2020-02-01 -vol 20"  7
test "T4.8" "-login ArtBekk -pass 3678 -role READ -res AV -ds 2020-01-01 -vol 20"  7

echo "Tests passed: $tests_passed"
echo "Tests failed: $tests_failed"
read
$HELL
if [ $tests_failed -eq 0 ]
then
exit 0
else
exit 1
fi