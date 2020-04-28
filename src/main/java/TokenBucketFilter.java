public class TokenBucketFilter {
    private int MAX_TOKENS;

    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens =0;

    public TokenBucketFilter(int maxTokens){
        MAX_TOKENS = maxTokens;
    }

    synchronized void getToken() throws InterruptedException {
        this.possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;
        if (this.possibleTokens > MAX_TOKENS) {
            this.possibleTokens = MAX_TOKENS;
        }

        if (this.possibleTokens == 0) {
            Thread.sleep(1000);
        } else {
            this.possibleTokens--;
        }

        this.lastRequestTime = System.currentTimeMillis();
        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
    }
}